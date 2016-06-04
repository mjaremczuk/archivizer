package pl.reveo.presentation.view.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import pl.reveo.presentation.R;
import pl.reveo.presentation.internal.di.components.DaggerContactDetailsComponent;
import pl.reveo.presentation.internal.di.modules.ContactDetailsModule;
import pl.reveo.presentation.presenter.ContactDetailsPresenter;
import pl.reveo.presentation.view.ContactDetailsDataView;
import pl.reveo.presentation.view.toolkit.DataLoader;

public class ContactDetailsActivity extends DefaultActivity implements ContactDetailsDataView {

    public static final String EXTRA_CONTACT_LOOK_UP_KEY = "contact_lookup_key";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.details_log) TextView detailsLogView;
    @Inject ContactDetailsPresenter presenter;
    private String lookupKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setDataView(this, lookupKey);
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
        if (getIntent() != null && getIntent().getExtras() != null &&
                getIntent().getExtras().containsKey(EXTRA_CONTACT_LOOK_UP_KEY)) {
            this.lookupKey = getIntent().getExtras().getString(EXTRA_CONTACT_LOOK_UP_KEY);
        }
    }

    @Override
    public void initializeInjector() {
        DaggerContactDetailsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .contactDetailsModule(new ContactDetailsModule())
                .build()
                .inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                ActivityCompat.finishAfterTransition(this);
                break;
            case R.id.details_data:
                presenter.loadData(DataLoader.CursorLoaderType.CONTACT_DATA);
                break;
            case R.id.details_sms:
                presenter.loadData(DataLoader.CursorLoaderType.PHONE);
                break;
            case R.id.details_email:
                presenter.loadData(DataLoader.CursorLoaderType.EMAIL);
                break;
        }
        return super.onOptionsItemSelected(item);
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

}
