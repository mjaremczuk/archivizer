package pl.reveo.presentation.presenter;

import android.support.annotation.NonNull;
import pl.reveo.domain.Meeting;
import pl.reveo.domain.exception.ErrorBundle;
import pl.reveo.domain.interactor.DefaultSubscriber;
import pl.reveo.domain.interactor.UseCase;
import pl.reveo.presentation.exception.ErrorMessageFactory;
import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.mapper.MeetingModelMapper;
import pl.reveo.presentation.model.MeetingModel;
import pl.reveo.presentation.view.MeetingView;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class MeetingPresenter implements Presenter {

	private MeetingView viewDetailsView;

	private final UseCase meetingUseCase;

	@Inject
	public MeetingPresenter(@Named("meeting") UseCase meetingUseCase) {
		this.meetingUseCase = meetingUseCase;
	}

	public void setView(@NonNull MeetingView view) {
		viewDetailsView = view;
	}

	@Override
	public void resume() {
	}

	@Override
	public void destroy() {
		meetingUseCase.unsubscribe();
	}

	/**
	 * Initializes the presenter by start retrieving fetchMeeting details.
	 */
	public void initialize(int meetingId) {
		meetingUseCase.execute(new LineupDetailsSubscriber());
	}

	private void showErrorMessage(ErrorBundle errorBundle) {
		String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.getContext(), errorBundle.getException());
		viewDetailsView.displayError(errorMessage);
	}

	private void showMeetingDetailsInView(Meeting meeting) {
		final MeetingModelMapper meetingModelMapper = new MeetingModelMapper();
		final MeetingModel meetingModel = meetingModelMapper.transform(meeting);
		viewDetailsView.renderMeeting(meetingModel);
	}

	private final class LineupDetailsSubscriber extends DefaultSubscriber<Meeting> {

		@Override
		public void onCompleted() {
			//todo UI
		}

		@Override
		public void onError(Throwable e) {
			//todo UI
		}

		@Override
		public void onNext(Meeting meeting) {
			showMeetingDetailsInView(meeting);
		}
	}
}
