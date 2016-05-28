package pl.reveo.presentation.presenter;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import pl.reveo.presentation.view.HomeDataView;


/**
 * Home presenter for contact
 */
public class HomePresenter extends DefaultPresenter<HomeDataView> implements LoaderManager.LoaderCallbacks {

    private static final String[] PROJECTION =
            {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER
            };

    private static final String SELECTION =
//            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " LIKE ?";
            ContactsContract.Contacts.HAS_PHONE_NUMBER + " == 1";
    HomeDataView dataView;
    LoaderManager loaderManager;
    long contactId;
    String contactKey;
    String searchString = "";
    private String[] mSelectionArgs = {searchString};

    public HomePresenter() {

    }

    public void initialize(HomeDataView dataView, LoaderManager loaderManager) {
        this.dataView = dataView;
        this.loaderManager = loaderManager;
        loaderManager.initLoader(0, null, this);
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        mSelectionArgs[0] = "%" + searchString + "%";
        return new CursorLoader(dataView.context()
                , ContactsContract.Contacts.CONTENT_URI
                , PROJECTION
                , SELECTION
                , null
                , ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        dataView.loadFinished((Cursor) data);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        dataView.loadFinished(null);
    }
}
