package pl.reveo.presentation.internal.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.reveo.data.executor.JobExecutor;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.executor.ThreadExecutor;
import pl.reveo.presentation.AndroidApplication;
import pl.reveo.presentation.UIThread;
import pl.reveo.presentation.navigation.Navigator;


/**
 * Application module.
 */
@Module
public class ApplicationModule {
	private final AndroidApplication application;

	public ApplicationModule(AndroidApplication application) {
		this.application = application;
	}

	@Provides
	@Singleton
	Context provideApplicationContext() {
		return this.application;
	}

	@Provides
	@Singleton
	Navigator provideNavigator() {
		return new Navigator();
	}

	@Provides
	@Singleton
	ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
		return jobExecutor;
	}

	@Provides
	@Singleton
	PostExecutionThread providePostExecutionThread(UIThread uiThread) {
		return uiThread;
	}


//	@Provides
//	@Singleton
//	com.benedeo.domain.repository.UserRepository provideUserRepository(UserDataRepository userDataRepository) {
//		return userDataRepository;
//	}
//
//

}
