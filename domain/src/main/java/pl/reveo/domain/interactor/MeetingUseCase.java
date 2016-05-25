package pl.reveo.domain.interactor;

import pl.reveo.domain.Meeting;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.executor.ThreadExecutor;
import pl.reveo.domain.repository.MeetingRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Meeting}.
 */
public class MeetingUseCase extends UseCase {

	private final MeetingRepository meetingRepository;

	private int meetingId;

	@Inject
	public MeetingUseCase(MeetingRepository meetingRepository, ThreadExecutor threadExecutor,
			PostExecutionThread postExecutionThread) {
		super(threadExecutor, postExecutionThread);
		this.meetingRepository = meetingRepository;
	}

	public void setMeetingId(int meetingId) {
		this.meetingId = meetingId;
	}

	@Override
	protected Observable buildUseCaseObservable() {
		return meetingRepository.fetchMeeting(meetingId);
	}
}
