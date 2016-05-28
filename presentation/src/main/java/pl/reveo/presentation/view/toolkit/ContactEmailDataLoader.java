package pl.reveo.presentation.view.toolkit;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;

/**
 * Created by michaljaremczuk on 24.05.2016.
 */
public class ContactEmailDataLoader extends CursorLoader {

    public ContactEmailDataLoader(CursorLoaderType type,Context context, String[] selectionArgs ) {
        super(context, type.URI, type.PROJECTION, type.SELECTION, selectionArgs, type.SORT);
    }



    public enum CursorLoaderType{

        EMAIL(ContactsContract.CommonDataKinds.Email.CONTENT_URI,EMAIL_PROJECTION, ContactEmailDataLoader.SELECTION,SORT_ORDER),
        PHONE(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,PHONES_PROJECTION,PHONE_SELECTION,PHONE_SORT_ORDER);

        private Uri URI;
        private String[] PROJECTION;
        private String SELECTION;
        private String SORT;

        CursorLoaderType(Uri URI, String[]projection, String selection, String sort) {
            this.URI = URI;
            this.PROJECTION = projection;
            this.SELECTION = selection;
            this.SORT = sort;
        }
    }


    private static final String[] EMAIL_PROJECTION = {
            ContactsContract.CommonDataKinds.Email._ID,
            ContactsContract.CommonDataKinds.Email.MIMETYPE,
            ContactsContract.CommonDataKinds.Email.ADDRESS,
            ContactsContract.CommonDataKinds.Email.TYPE,
            ContactsContract.CommonDataKinds.Email.LABEL,
            ContactsContract.CommonDataKinds.Email.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Email.CONTACT_STATUS_LABEL
    };
    private static final String[] NICKNAME_PROJECTION = {
            ContactsContract.CommonDataKinds.Nickname._ID,
            ContactsContract.CommonDataKinds.Nickname.MIMETYPE,
            ContactsContract.CommonDataKinds.Nickname.NAME,
            ContactsContract.CommonDataKinds.Nickname.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Nickname.LABEL,
            ContactsContract.CommonDataKinds.Nickname.TYPE
    };
    private static final String[] PHONES_PROJECTION = {
            ContactsContract.CommonDataKinds.Phone._ID,
            ContactsContract.CommonDataKinds.Phone.MIMETYPE,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.LABEL,
            ContactsContract.CommonDataKinds.Phone.TYPE
    };

    // Defines the SELECTION clause
    private static final String SELECTION = ContactsContract.CommonDataKinds.Email.LOOKUP_KEY + " = ?";
    /*
    * Defines a string that specifies a SORT order of MIME type
    */
    private static final String SORT_ORDER = ContactsContract.CommonDataKinds.Email.MIMETYPE;

    // Defines the SELECTION clause
    private static final String PHONE_SELECTION = ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY + " = ?";
    /*
    * Defines a string that specifies a SORT order of MIME type
    */
    private static final String PHONE_SORT_ORDER = ContactsContract.CommonDataKinds.Phone.MIMETYPE;


}
