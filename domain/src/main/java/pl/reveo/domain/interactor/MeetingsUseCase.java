package pl.reveo.domain.interactor;

import pl.reveo.domain.Meeting;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.repository.MeetingRepository;
import pl.reveo.domain.executor.ThreadExecutor;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link pl.reveo.domain.interactor.UseCase} that represents a use case for
 * retrieving a collection of all {@link Meeting}.
 */
public class MeetingsUseCase extends UseCase {

	private final MeetingRepository meetingRepository;

	@Inject
	public MeetingsUseCase(
			MeetingRepository meetingRepository, ThreadExecutor threadExecutor,
			PostExecutionThread postExecutionThread) {
		super(threadExecutor, postExecutionThread);
		this.meetingRepository = meetingRepository;
	}

	@Override
	public Observable buildUseCaseObservable() {
		return meetingRepository.fetchMeetings();
	}
}
