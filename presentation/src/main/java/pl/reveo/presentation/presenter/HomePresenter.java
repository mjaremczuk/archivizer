package pl.reveo.presentation.presenter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import javax.inject.Inject;

import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.view.HomeDataView;

/**
 * Home presenter for contact
 */
@PerActivity
public class HomePresenter extends DefaultPresenter<HomeDataView> implements LoaderManager.LoaderCallbacks {

    private static final String[] PROJECTION =
            {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.LOOKUP_KEY,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                    ContactsContract.Contacts.HAS_PHONE_NUMBER
            };

    private static final String SELECTION = ContactsContract.Contacts.HAS_PHONE_NUMBER + " == 1";

    HomeDataView dataView;
    long contactId;
    String contactKey;
    String searchString = "";
    private String[] mSelectionArgs = { searchString };
    ContentResolver contentResolver;
    LoaderManager loaderManager;

    @Inject
    public HomePresenter(Context context,ContentResolver contentResolver, LoaderManager loaderManager) {
        super(context);
        this.contentResolver = contentResolver;
        this.loaderManager = loaderManager;
    }

    public void initialize(HomeDataView dataView) {
        this.dataView = dataView;
        loaderManager.initLoader(0, null, this);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        mSelectionArgs[0] = "%" + searchString + "%";
        return new CursorLoader(getContext()
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
