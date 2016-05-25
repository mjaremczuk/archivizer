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
import pl.reveo.presentation.view.MeetingsView;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class MeetingsPresenter extends DefaultSubscriber<List<Meeting>> implements Presenter {

	private MeetingsView viewListView;
	private final UseCase meetingsUseCase;

	@Inject
	public MeetingsPresenter(@Named("meetings") UseCase meetingsUseCase) {
		this.meetingsUseCase = meetingsUseCase;
	}

	public void setView(@NonNull MeetingsView view) {
		viewListView = view;
	}

	@Override
	public void resume() {
	}

	@Override
	public void destroy() {
		meetingsUseCase.unsubscribe();
	}

	/**
	 * Initializes the presenter by start retrieving the fetchMeeting list.
	 */
	public void initialize() {
		meetingsUseCase.execute(new LineupListSubscriber());
	}

	private void showErrorMessage(ErrorBundle errorBundle) {
		String errorMessage = ErrorMessageFactory.create(this.viewListView.getContext(), errorBundle.getException());
		viewListView.displayError(errorMessage);
	}

	private void showLineupsCollectionInView(List<Meeting> meetingsCollection) {
		final MeetingModelMapper meetingModelMapper = new MeetingModelMapper();
		final List<MeetingModel> meetingModelCollection = meetingModelMapper.transform(meetingsCollection);
		viewListView.renderMeetings(meetingModelCollection);
	}

	private final class LineupListSubscriber extends DefaultSubscriber<List<Meeting>> {

		@Override
		public void onCompleted() {
			//todo UI hide loader
		}

		@Override
		public void onError(Throwable e) {
			//todo UI show error
		}

		@Override
		public void onNext(List<Meeting> meetings) {
			//todo display data
			showLineupsCollectionInView(meetings);
		}
	}
}
