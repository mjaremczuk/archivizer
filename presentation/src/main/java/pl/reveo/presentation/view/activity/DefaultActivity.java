package pl.reveo.presentation.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import pl.reveo.presentation.AndroidApplication;
import pl.reveo.presentation.internal.di.components.ApplicationComponent;
import pl.reveo.presentation.internal.di.modules.ActivityModule;
import pl.reveo.presentation.navigation.Navigator;
import javax.inject.Inject;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class DefaultActivity extends Activity {

	@Inject
	Navigator navigator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getApplicationComponent().inject(this);
	}

	/**
	 * Adds a {@link Fragment} to this activity's layout.
	 *
	 * @param containerViewId The container view to where add the fragment.
	 * @param fragment The fragment to be added.
	 */
	protected void addFragment(int containerViewId, Fragment fragment) {
		FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
		fragmentTransaction.add(containerViewId, fragment);
		fragmentTransaction.commit();
	}

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
	protected ActivityModule getActivityModule() {
		return new ActivityModule(this);
	}
}
