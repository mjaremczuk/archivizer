package pl.reveo.presentation.presenter;

import android.support.annotation.NonNull;
import pl.reveo.domain.Photo;
import pl.reveo.domain.exception.ErrorBundle;
import pl.reveo.domain.interactor.DefaultSubscriber;
import pl.reveo.domain.interactor.UseCase;
import pl.reveo.presentation.exception.ErrorMessageFactory;
import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.mapper.PhotoModelMapper;
import pl.reveo.presentation.model.PhotoModel;
import pl.reveo.presentation.view.PhotoView;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class PhotoPresenter implements Presenter {

	private PhotoView viewDetailsView;

	private final UseCase photoUseCase;

	@Inject
	public PhotoPresenter(@Named("photo") UseCase photoUseCase) {
		this.photoUseCase = photoUseCase;
	}

	public void setView(@NonNull PhotoView view) {
		viewDetailsView = view;
	}

	@Override
	public void resume() {
	}

	@Override
	public void destroy() {
		photoUseCase.unsubscribe();
	}

	/**
	 * Initializes the presenter by start retrieving fetchPhoto details.
	 */
	public void initialize(int photoId) {
		photoUseCase.execute(new LineupDetailsSubscriber());
	}

	private void showErrorMessage(ErrorBundle errorBundle) {
		String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.getContext(), errorBundle.getException());
		viewDetailsView.displayError(errorMessage);
	}

	private void showPhotoDetailsInView(Photo photo) {
		final PhotoModelMapper photoModelMapper = new PhotoModelMapper();
		final PhotoModel photoModel = photoModelMapper.transform(photo);
		viewDetailsView.renderPhoto(photoModel);
	}

	private final class LineupDetailsSubscriber extends DefaultSubscriber<Photo> {

		@Override
		public void onCompleted() {
			//todo UI
		}

		@Override
		public void onError(Throwable e) {
			//todo UI
		}

		@Override
		public void onNext(Photo photo) {
			showPhotoDetailsInView(photo);
		}
	}
}
