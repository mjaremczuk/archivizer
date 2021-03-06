package pl.reveo.presentation.internal.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.executor.ThreadExecutor;
import pl.reveo.presentation.internal.di.modules.ApplicationModule;
import pl.reveo.presentation.navigation.Navigator;
import pl.reveo.presentation.view.activity.DefaultActivity;


/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
	void inject(DefaultActivity defaultActivity);

	//Exposed to sub-graphs.
	Context context();

	ThreadExecutor threadExecutor();

	PostExecutionThread postExecutionThread();

	Navigator navigator();

}
