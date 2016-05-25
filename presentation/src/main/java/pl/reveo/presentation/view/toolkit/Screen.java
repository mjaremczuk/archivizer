package pl.reveo.presentation.view.toolkit;

import android.content.Context;
import android.os.Bundle;

/**
 * Screen abstract class.
 */
public abstract class Screen {

	private Bundle bundle = new Bundle();

	public void onCreateLifecycle(Context context, ContainerView parent) {

	}

	public void onSaveStateLifeCycle(Bundle outState) {

	}

	public void onDestroyLifecycle(ContainerView parent) {

	}

	public Bundle getBundle() {
		return bundle;
	}

	public void setBundle(Bundle bundle) {
		this.bundle = bundle;
	}
}
