package pl.reveo.presentation.view;

import android.database.Cursor;


public interface HomeDataView extends  LoadDataView {

    void loadFinished(Cursor data);
}
