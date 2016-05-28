package pl.reveo.presentation.view;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

/**
 * Contact details data view
 */
public interface ContactDetailsDataView {


    Context context();

    void loadFinished(Cursor data);

    void loadReset();

    void displayDataLog(String data);

    ContentResolver contentResolver();
}
