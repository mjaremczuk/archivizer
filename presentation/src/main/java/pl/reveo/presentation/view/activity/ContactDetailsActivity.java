package pl.reveo.presentation.view.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import pl.reveo.presentation.R;
import pl.reveo.presentation.presenter.ContactDetailsPresenter;
import pl.reveo.presentation.view.ContactDetailsDataView;


public class ContactDetailsActivity extends DefaultActivity implements ContactDetailsDataView {

    public static final String EXTRA_CONTACT_LOOK_UP_KEY = "contact_lookup_key";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.details_log)
    TextView detailsLogView;


    private String lookupKey;
    ContactDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactDetailsPresenter();
        presenter.setDataView(this,getSupportLoaderManager(),lookupKey);
    }

    @Override
    public void setUpActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int layoutResource() {
        return R.layout.activity_contact_details;
    }

    @Override
    public void readExtraData() {
        if(getIntent() != null && getIntent().getExtras() != null && getIntent().getExtras().containsKey(EXTRA_CONTACT_LOOK_UP_KEY)) {
            this.lookupKey = getIntent().getExtras().getString(EXTRA_CONTACT_LOOK_UP_KEY);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            ActivityCompat.finishAfterTransition(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void loadFinished(Cursor data) {

    }

    @Override
    public void loadReset() {

    }

    @Override
    public void displayDataLog(String data) {
        detailsLogView.setText(data);
    }

    @Override
    public ContentResolver contentResolver() {
        return getContentResolver();
    }
}
