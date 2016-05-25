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
import pl.reveo.presentation.view.PhotosView;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class PhotosPresenter extends DefaultSubscriber<List<Photo>> implements Presenter {

	private PhotosView viewListView;
	private final UseCase photosUseCase;

	@Inject
	public PhotosPresenter(@Named("photos") UseCase photosUseCase) {
		this.photosUseCase = photosUseCase;
	}

	public void setView(@NonNull PhotosView view) {
		viewListView = view;
	}

	@Override
	public void resume() {
	}

	@Override
	public void destroy() {
		photosUseCase.unsubscribe();
	}

	/**
	 * Initializes the presenter by start retrieving the fetchPhoto list.
	 */
	public void initialize() {
		photosUseCase.execute(new LineupListSubscriber());
	}

	private void showErrorMessage(ErrorBundle errorBundle) {
		String errorMessage = ErrorMessageFactory.create(this.viewListView.getContext(), errorBundle.getException());
		viewListView.displayError(errorMessage);
	}

	private void showLineupsCollectionInView(List<Photo> photosCollection) {
		final PhotoModelMapper photoModelMapper = new PhotoModelMapper();
		final List<PhotoModel> photoModelCollection = photoModelMapper.transform(photosCollection);
		viewListView.renderPhotos(photoModelCollection);
	}

	private final class LineupListSubscriber extends DefaultSubscriber<List<Photo>> {

		@Override
		public void onCompleted() {
			//todo UI hide loader
		}

		@Override
		public void onError(Throwable e) {
			//todo UI show error
		}

		@Override
		public void onNext(List<Photo> photos) {
			//todo display data
			showLineupsCollectionInView(photos);
		}
	}
}
