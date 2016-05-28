package pl.reveo.presentation.view.activity;

import android.Manifest;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
import pl.reveo.presentation.R;
import pl.reveo.presentation.presenter.HomePresenter;
import pl.reveo.presentation.view.HomeDataView;
import pl.reveo.presentation.view.adapter.ContactAdapter;
import pl.reveo.presentation.view.toolkit.RecyclerItemClickListener;


@RuntimePermissions
public class HomeActivity extends DefaultActivity implements HomeDataView, RecyclerItemClickListener.OnItemClickListener {

    private static final String TAG = HomeActivity.class.getName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    HomePresenter presenter;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ContactAdapter(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,this));
        presenter = new HomePresenter();
        HomeActivityPermissionsDispatcher.getContactsWithCheck(this);
    }

    @Override
    public void setUpActionBar() {
        setSupportActionBar(toolbar);
    }

    @Override
    public int layoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public void readExtraData() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HomeActivityPermissionsDispatcher.onRequestPermissionsResult(HomeActivity.this,requestCode,grantResults);
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void loadFinished(Cursor data) {
        adapter.changeCursor(data);
    }

    @NeedsPermission({Manifest.permission.READ_CONTACTS,Manifest.permission.READ_SMS})
    public void getContacts() {
        presenter.initialize(this, getSupportLoaderManager());
    }

    @OnShowRationale({Manifest.permission.READ_CONTACTS,Manifest.permission.READ_SMS})
    public void showRationale(PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("Application require permission")
                .setPositiveButton(android.R.string.ok, (dialog, button) -> request.proceed())
                .setNegativeButton(android.R.string.cancel, (dialog, button) -> request.cancel())
                .show();
    }

    @Override
    public void onItemClick(View view, int position) {
        String lookupKey = adapter.getItemLookupKey(position);
        Log.d(TAG, "onItemClick: lookupkey:"+ lookupKey);
        navigator.navigateToContactDetails(HomeActivity.this,lookupKey);
    }
}
