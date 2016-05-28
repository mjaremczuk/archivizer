package pl.reveo.presentation.view;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by michaljaremczuk on 15.05.2016.
 */
public interface HomeDataView {


    Context context();

    void loadFinished(Cursor data);
}
