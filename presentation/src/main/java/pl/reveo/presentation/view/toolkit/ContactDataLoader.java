package pl.reveo.presentation.view.toolkit;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;

/**
 * Created by michaljaremczuk on 24.05.2016.
 */
public class ContactDataLoader extends CursorLoader {

    private static final String[] PROJECTION = {
            ContactsContract.Contacts.Data._ID,
            ContactsContract.Contacts.Data.MIMETYPE,
            ContactsContract.Contacts.Data.DATA1,
            ContactsContract.Contacts.Data.DATA2,
            ContactsContract.Contacts.Data.DATA3,
            ContactsContract.Contacts.Data.DATA4,
            ContactsContract.Contacts.Data.DATA5,
            ContactsContract.Contacts.Data.DATA6,
            ContactsContract.Contacts.Data.DATA7,
            ContactsContract.Contacts.Data.DATA8,
            ContactsContract.Contacts.Data.DATA9,
            ContactsContract.Contacts.Data.DATA10,
            ContactsContract.Contacts.Data.DATA11,
            ContactsContract.Contacts.Data.DATA12,
            ContactsContract.Contacts.Data.DATA13,
            ContactsContract.Contacts.Data.DATA14,
            ContactsContract.Contacts.Data.DATA15
    };
    // Defines the selection clause
    private static final String SELECTION = ContactsContract.Data.LOOKUP_KEY + " = ?";
    /*
    * Defines a string that specifies a sort order of MIME type
    */
    private static final String SORT_ORDER = ContactsContract.Data.MIMETYPE;

    public ContactDataLoader(Context context,String[] selectionArgs) {
        super(context, ContactsContract.Data.CONTENT_URI, PROJECTION, SELECTION, selectionArgs, SORT_ORDER);
    }
}
