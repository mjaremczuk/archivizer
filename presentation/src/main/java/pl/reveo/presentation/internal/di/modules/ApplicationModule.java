package pl.reveo.presentation.internal.di.modules;

import android.content.Context;
import pl.reveo.data.cache.TemplateCache;
import pl.reveo.data.cache.iface.ITemplateCache;
import pl.reveo.data.executor.JobExecutor;
import pl.reveo.data.orm.DatabaseHelper;
import pl.reveo.data.repository.TemplateDataRepository;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.executor.ThreadExecutor;
import pl.reveo.domain.repository.TemplateRepository;
import pl.reveo.presentation.AndroidApplication;
import pl.reveo.presentation.UIThread;
import pl.reveo.presentation.navigation.Navigator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import pl.reveo.data.cache.iface.IPhotoCache;
import pl.reveo.data.cache.PhotoCache;
import pl.reveo.data.repository.PhotoDataRepository;
import pl.reveo.domain.repository.PhotoRepository;
import pl.reveo.data.cache.iface.IUserCache;
import pl.reveo.data.cache.UserCache;
import pl.reveo.data.repository.UserDataRepository;
import pl.reveo.domain.repository.UserRepository;
import pl.reveo.data.cache.iface.IMeetingCache;
import pl.reveo.data.cache.MeetingCache;
import pl.reveo.data.repository.MeetingDataRepository;
import pl.reveo.domain.repository.MeetingRepository;


/**
 * Dagger module that provides objects which will live during the application lifecycle.
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
		return application;
	}

	@Provides
	@Singleton
	DatabaseHelper provideDatabaseHelper(Context context) {
		return new DatabaseHelper(context);
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

	@Provides
	@Singleton
	ITemplateCache provideTemplateCache(TemplateCache templateCache) {
		return templateCache;
	}

	@Provides
	@Singleton
	TemplateRepository provideTemplateRepository(TemplateDataRepository templateDataRepository) {
		return templateDataRepository;
	}

  @Provides
  @Singleton
  IPhotoCache providePhotoCache(PhotoCache photoCache) {
    return photoCache;
  }

  @Provides
  @Singleton
  PhotoRepository providePhotoRepository(PhotoDataRepository photoDataRepository) {
    return photoDataRepository;
  }
  @Provides
  @Singleton
  IUserCache provideUserCache(UserCache userCache) {
    return userCache;
  }

  @Provides
  @Singleton
  UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }
  @Provides
  @Singleton
  IMeetingCache provideMeetingCache(MeetingCache meetingCache) {
    return meetingCache;
  }

  @Provides
  @Singleton
  MeetingRepository provideMeetingRepository(MeetingDataRepository meetingDataRepository) {
    return meetingDataRepository;
  }

}
