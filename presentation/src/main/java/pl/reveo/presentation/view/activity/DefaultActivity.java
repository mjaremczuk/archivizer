package pl.reveo.presentation.view.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import pl.reveo.presentation.AndroidApplication;
import pl.reveo.presentation.R;
import pl.reveo.presentation.internal.di.components.ApplicationComponent;
import pl.reveo.presentation.internal.di.modules.ActivityModule;
import pl.reveo.presentation.navigation.Navigator;


public abstract class DefaultActivity extends AppCompatActivity {

    @Inject
    Navigator navigator;
    View snackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResource());
        getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        setUpActionBar();
        readExtraData();
    }

    public abstract void setUpActionBar();
    public abstract int layoutResource();
    public abstract void readExtraData();

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /**
     * Displays not ready yet.
     */
    public void notReadyYet() {
        Toast.makeText(this, R.string.not_ready_yet, Toast.LENGTH_LONG).show();
    }

    /**
     * Display snack bar with message
     *
     * @param message message to display
     */
    public void displayError(String message) {
        if (snackView == null) {
            ViewGroup viewGroup = ((ViewGroup) this.findViewById(android.R.id.content));
            snackView =  viewGroup != null ? viewGroup.getChildAt(0) : null;
        }
        Snackbar snackbar = Snackbar.make(snackView, message, Snackbar.LENGTH_SHORT);
        ViewGroup group = (ViewGroup) snackbar.getView();
        group.setBackgroundResource(R.color.snackbar_background);
        snackbar.show();
    }

    /**
     * Display snack bar with message and custom background color
     *
     * @param message message to display
     * @param colorResource background color resource
     */
    public void displayMessage(String message, int colorResource) {
        if (snackView == null) {
            ViewGroup viewGroup = ((ViewGroup) this.findViewById(android.R.id.content));
            snackView = viewGroup != null ? viewGroup.getChildAt(0) : null;
        }
        Snackbar snackbar = Snackbar.make(snackView, message, Snackbar.LENGTH_SHORT);
        ViewGroup group = (ViewGroup) snackbar.getView();
        group.setBackgroundResource(colorResource);
        snackbar.show();
    }
}
