package pl.reveo.presentation.internal.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import pl.reveo.presentation.internal.di.PerActivity;


/**
 * Activity module.
 */
@Module
public class ActivityModule {
	private final Activity activity;

	public ActivityModule(Activity activity) {
		this.activity = activity;
	}

	/**
	 * Expose the activity to dependents in the graph.
	 */
	@Provides
	@PerActivity
	Activity activity() {
		return this.activity;
	}
}
