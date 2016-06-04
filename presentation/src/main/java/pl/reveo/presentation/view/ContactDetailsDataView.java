package pl.reveo.presentation.view;

import android.database.Cursor;

/**
 * Contact details data view
 */
public interface ContactDetailsDataView extends LoadDataView{

    void loadFinished(Cursor data);

    void loadReset();

    void displayDataLog(String data);
}
