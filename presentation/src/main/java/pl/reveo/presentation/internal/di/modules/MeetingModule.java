package pl.reveo.presentation.internal.di.modules;

import pl.reveo.domain.interactor.MeetingUseCase;
import pl.reveo.domain.interactor.MeetingsUseCase;
import pl.reveo.domain.interactor.UseCase;
import pl.reveo.presentation.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Dagger module that provides fetchMeeting related collaborators.
 */
@Module
public class MeetingModule {

	public MeetingModule() {
	}

	@Provides
	@PerActivity
	@Named("meetings")
	UseCase provideMeetingsUseCase(MeetingsUseCase meetingsUseCase) {
		return meetingsUseCase;
	}

	@Provides
	@PerActivity
	@Named("meeting")
	UseCase provideMeetingUseCase(MeetingUseCase meetingUseCase) {
		return meetingUseCase;
	}
}