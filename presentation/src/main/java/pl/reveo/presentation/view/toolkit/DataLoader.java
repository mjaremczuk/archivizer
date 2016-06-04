package pl.reveo.presentation.view.toolkit;

import android.content.Context;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.CursorLoader;

import lombok.Data;

@Data
public class DataLoader extends CursorLoader {

    public static final int CONTACT_DATA_ID = 0;
    public static final int EMAIL_ID = 1;
    public static final int PHONE_ID = 2;

    CursorLoaderType loaderType;

    public DataLoader(CursorLoaderType type, Context context, String[] selectionArgs ) {
        super(context, type.URI, type.PROJECTION, type.SELECTION, selectionArgs, type.SORT);
        this.loaderType = type;
    }

    public enum CursorLoaderType{

        CONTACT_DATA(CONTACT_DATA_ID,ContactsContract.Data.CONTENT_URI,DATA_PROJECTION,DATA_SELECTION,DATA_SORT_ORDER),
        EMAIL(EMAIL_ID,ContactsContract.CommonDataKinds.Email.CONTENT_URI, EMAIL_PROJECTION, DataLoader.SELECTION, SORT_ORDER),
        PHONE(PHONE_ID,ContactsContract.CommonDataKinds.Phone.CONTENT_URI,PHONES_PROJECTION,PHONE_SELECTION,PHONE_SORT_ORDER);

        public Uri URI;
        public String[] PROJECTION;
        public String SELECTION;
        public String SORT;
        public final int ID;

        CursorLoaderType(int id,Uri URI, String[]projection, String selection, String sort) {
            this.ID = id;
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



    private static final String[] DATA_PROJECTION = {
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
    private static final String DATA_SELECTION = ContactsContract.Data.LOOKUP_KEY + " = ?";
    /*
    * Defines a string that specifies a sort order of MIME type
    */
    private static final String DATA_SORT_ORDER = ContactsContract.Data.MIMETYPE;


}
