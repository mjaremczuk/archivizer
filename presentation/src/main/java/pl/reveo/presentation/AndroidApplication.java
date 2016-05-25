package pl.reveo.presentation;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;
import com.crashlytics.android.Crashlytics;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.Iconics;
import pl.reveo.presentation.internal.di.components.ApplicationComponent;
import pl.reveo.presentation.internal.di.components.DaggerApplicationComponent;
import pl.reveo.presentation.internal.di.modules.ApplicationModule;
import io.fabric.sdk.android.Fabric;
import net.danlew.android.joda.JodaTimeAndroid;
import timber.log.Timber;
import timber.log.Timber.DebugTree;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Android Main Application
 */
public class AndroidApplication extends Application {

	private ApplicationComponent applicationComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		if (BuildConfig.CRASHLYTICS_ENABLED) {
			Timber.plant(new CrashReportingTree());
			Fabric.with(this, new Crashlytics());
		}
		else {
			Timber.plant(new DebugTree());
		}
		Iconics.init(getApplicationContext());
		Iconics.registerFont(new GoogleMaterial());
		Iconics.registerFont(new CommunityMaterial());
		initializeCalligraphy();
		initializeInjector();
		initializeJodaTime();
	}

	private void initializeInjector() {
		applicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.build();
	}

	/**
	 * Initializes calligraphy library.
	 * @TODO uncomment if needed or remove in not
	 */
	private void initializeCalligraphy() {
//		Configuration configuration = getResources().getConfiguration();
//		configuration.fontScale = 1.0f;
//		CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//				.setDefaultFontPath(getString(R.string.font_roboto_regular))
//				.setFontAttrId(R.attr.fontPath)
//				.build());
//		getBaseContext().getResources().updateConfiguration(configuration, null);
	}

	/**
	 * Initialize joda time.
	 */
	private void initializeJodaTime() {
		JodaTimeAndroid.init(this);
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(base);
	}

	public ApplicationComponent getApplicationComponent() {
		return applicationComponent;
	}

	/**
	 * A tree which logs important information for crash reporting.
	 */
	private static class CrashReportingTree extends Timber.DebugTree {
		@Override
		public void i(String message, Object... args) {
			if (args != null && args.length > 0) {
				message = String.format(message, args);
			}
			Crashlytics.log(message);
		}

		@Override
		public void i(Throwable t, String message, Object... args) {
			i(message, args); // Just add to the log.
		}

		@Override
		public void e(String message, Object... args) {
			i("ERROR: " + message, args);
		}

		@Override
		public void e(Throwable t, String message, Object... args) {
			e(message, args);
			Crashlytics.logException(t);
		}

		@Override
		public void d(String message, Object... args) {
			super.d(message, args);
		}

		@Override
		public void w(String message, Object... args) {
			i("WARNING: " + message, args);
		}

		@Override
		public void w(Throwable t, String message, Object... args) {
			i("WARNING: " + message, args);
		}
	}
}
