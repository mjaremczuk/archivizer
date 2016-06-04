package pl.reveo.presentation.internal.di.modules;

import android.app.Activity;
import android.content.ContentResolver;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import pl.reveo.presentation.internal.di.PerActivity;

/**
 * Activity module.
 */
@Module
public class ActivityModule {
    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
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

    @Provides
    @PerActivity
    ContentResolver provideContentResolver() {
        return activity.getContentResolver();
    }

    @Provides
    @PerActivity
    LoaderManager provideLoaderManager() {
        return activity.getSupportLoaderManager();
    }
}
