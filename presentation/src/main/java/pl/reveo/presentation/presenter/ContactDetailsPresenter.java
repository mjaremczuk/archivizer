package pl.reveo.presentation.presenter;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import pl.reveo.presentation.view.ContactDetailsDataView;
import pl.reveo.presentation.view.toolkit.ContactDataLoader;
import pl.reveo.presentation.view.toolkit.ContactEmailDataLoader;


/**
 * Contact details presenter
 */
public class ContactDetailsPresenter extends DefaultPresenter<ContactDetailsDataView> implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DETAILS_QUERY_ID = 1;
    private static final int DETAILS_PHONE_QUERY_ID = 2;
    // Defines the array to hold the search criteria
    private String[] mSelectionArgs = {""};
    /*
     * Defines a variable to contain the selection value. Once you
     * have the Cursor from the Contacts table, and you've selected
     * the desired row, move the row's LOOKUP_KEY value into this
     * variable.
     */
    private String mLookupKey;

    private LoaderManager loadManager;


    public ContactDetailsPresenter() {

    }

    public void setDataView(ContactDetailsDataView dataView, LoaderManager manager, String mLookupKey) {
        super.setDataView(dataView);
        this.loadManager = manager;
        this.mLookupKey = mLookupKey;
        loadManager.initLoader(DETAILS_QUERY_ID, null, this);
//        loadManager.initLoader(DETAILS_PHONE_QUERY_ID, null, this);
    }

    public void fetchContactDetails() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Choose the proper action
        Loader loader = null;
        switch (id) {
            case DETAILS_QUERY_ID:
                // Assigns the selection parameter
                mSelectionArgs[0] = mLookupKey;
                // Starts the query
                loader = new ContactDataLoader(dataView.context(),mSelectionArgs);
                break;
            case DETAILS_PHONE_QUERY_ID:
                loader = new ContactEmailDataLoader(ContactEmailDataLoader.CursorLoaderType.PHONE,dataView.context(),mSelectionArgs);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        switch (loader.getId()) {
            case DETAILS_QUERY_ID:
                String dataString = cursorRowToString(data);
                dataView.loadFinished(data);
                dataView.displayDataLog(dataString );
                Log.d("Presenter", "onLoadFinished: cursor to String: "+ dataString);
                break;
            case DETAILS_PHONE_QUERY_ID:
                String[] phones = getPhoneNumbers(data);
                Uri smsUri = Uri.parse("content://sms/inbox");
                String SELECTION =  phones.length == 1 ? " address =  ?" : phoneSelectionString(phones.length) ;
                String SelectionArgs[] = phones ;
                Cursor smsCUrsor = dataView.contentResolver().query(smsUri,null,SELECTION,SelectionArgs,null);
//                Log.d("EMAIL", "onLoadFinished: cursor: " + dataString);
                dataView.displayDataLog(cursorRowToString(smsCUrsor));
                break;
        }
        data.close();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dataView.loadReset();
    }

    private String cursorRowToString(Cursor cursor) {
        StringBuilder builder = new StringBuilder();
        while(cursor.moveToNext()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                builder.append(cursor.getColumnName(i)).append(": ");
                builder.append(getDisplayableValueForColumn(cursor,i)).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private String getDisplayableValueForColumn(Cursor cursor, int columnIndex){
        String displayableValue = "";
        switch (cursor.getType(columnIndex)){
            case Cursor.FIELD_TYPE_INTEGER:
                displayableValue = String.valueOf(cursor.getInt(columnIndex));
                break;
            case Cursor.FIELD_TYPE_FLOAT:
                displayableValue = String.valueOf(cursor.getFloat(columnIndex));
                break;
            case Cursor.FIELD_TYPE_NULL:
                displayableValue = "NULL";
                break;
            case Cursor.FIELD_TYPE_STRING:
                displayableValue = cursor.getString(columnIndex);
                break;
        }
        return  displayableValue;
    }

    private String[] getPhoneNumbers(Cursor cursor){
        String[] phones = new String[cursor.getCount()];
        int pos = 0;
        while (cursor.moveToNext()){
            phones[pos] = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replace("-","");
            pos++;
        }
        return phones;
    }

    private String phoneSelectionString(int count){
        StringBuilder builder = new StringBuilder("address = ? ");
        if(count > 1){
            for (int i = 0; i < count; i++) {
                builder.append(" or address = ? ");
            }

        }
        return builder.toString();
    }
}
