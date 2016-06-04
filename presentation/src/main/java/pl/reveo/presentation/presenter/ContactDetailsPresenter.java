package pl.reveo.presentation.presenter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import pl.reveo.presentation.view.ContactDetailsDataView;
import pl.reveo.presentation.view.toolkit.DataLoader;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Contact details presenter
 */
public class ContactDetailsPresenter extends DefaultPresenter<ContactDetailsDataView>
        implements LoaderManager.LoaderCallbacks<Cursor> {

    //content://sms/inbox - for only received
    private static final String SMS_INBOX = "content://sms/";
    private String[] mSelectionArgs = { "" };
    private String mLookupKey;

    DataLoader.CursorLoaderType CURRENT_DATA_TYPE;
    private LoaderManager loadManager;
    private ContentResolver contentResolver;

    public ContactDetailsPresenter(Context context, ContentResolver contentResolver, LoaderManager loaderManager) {
        super(context);
        this.contentResolver = contentResolver;
        this.loadManager = loaderManager;
    }

    public void setDataView(ContactDetailsDataView dataView, String mLookupKey) {
        super.setDataView(dataView);
        this.mLookupKey = mLookupKey;
        CURRENT_DATA_TYPE = DataLoader.CursorLoaderType.CONTACT_DATA;
        loadManager.initLoader(CURRENT_DATA_TYPE.ID, null, this);
    }

    public void loadData(DataLoader.CursorLoaderType dataType) {
        loadManager.destroyLoader(CURRENT_DATA_TYPE.ID);
        this.CURRENT_DATA_TYPE = dataType;
        loadManager.initLoader(CURRENT_DATA_TYPE.ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        mSelectionArgs[0] = mLookupKey;
        return new DataLoader(CURRENT_DATA_TYPE, getContext(), mSelectionArgs);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        DataLoader mLoader = (DataLoader) loader;
        switch (mLoader.getId()) {
            case DataLoader.CONTACT_DATA_ID:
            case DataLoader.EMAIL_ID:
                String dataString = cursorRowToString(data);
                dataView.loadFinished(data);
                dataView.displayDataLog(dataString);
                Log.d("Presenter", "onLoadFinished: cursor to String: " + dataString);
                break;
            case DataLoader.PHONE_ID:
                String[] phones = getPhoneNumbers(data);
                loadManager.destroyLoader(CURRENT_DATA_TYPE.ID);
                contactSmsList(phones);
                dataView.displayDataLog(phones[0]);
                break;
        }
    }

    private void contactSmsList(String... phones) {
        Observable.just(smsList(phones)).observeOn(AndroidSchedulers.mainThread()
                ).subscribeOn(Schedulers.newThread()).subscribe(smsData -> {
            dataView.displayDataLog(smsData);
        });
    }

    private String smsList(String... phones) {
        // TODO: 04.06.2016 i didnt inspected deeply why mainly it doesnt return any records for contact which in fact wrote messages
        // TODO: 04.06.2016  but not for all of contacts, some of them work correct - display 
        Uri smsUri = Uri.parse(SMS_INBOX);
        String SELECTION = phoneSelectionString(phones.length);
        String[] PROJECTION = {
                "address",
                "body",
                "date",
                "person"
        };
        Cursor smsCursor = contentResolver.query(smsUri, PROJECTION, SELECTION, phones, null);
        return cursorRowToString(smsCursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        dataView.loadReset();
    }

    private String cursorRowToString(Cursor cursor) {
        StringBuilder builder = new StringBuilder();
        while (cursor.moveToNext()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                builder.append(cursor.getColumnName(i)).append(": ");
                builder.append(getDisplayableValueForColumn(cursor, i)).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private String getDisplayableValueForColumn(Cursor cursor, int columnIndex) {
        String displayableValue = "";
        switch (cursor.getType(columnIndex)) {
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
        return displayableValue;
    }

    private String[] getPhoneNumbers(Cursor cursor) {
        String[] phones = new String[cursor.getCount()];
        int pos = 0;
        while (cursor.moveToNext()) {
            phones[pos] = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    .replace("-", "");
            pos++;
        }
        return phones;
    }

    private String phoneSelectionString(int count) {
        StringBuilder builder = new StringBuilder("address = ? ");
        if (count > 1) {
            for (int i = 0; i < count; i++) {
                builder.append(" or address = ? ");
            }
        }
        return builder.toString();
    }
}
