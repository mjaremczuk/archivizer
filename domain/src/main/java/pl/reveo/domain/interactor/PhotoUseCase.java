package pl.reveo.domain.interactor;

import pl.reveo.domain.Photo;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.executor.ThreadExecutor;
import pl.reveo.domain.repository.PhotoRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link Photo}.
 */
public class PhotoUseCase extends UseCase {

	private final PhotoRepository photoRepository;

	private int photoId;

	@Inject
	public PhotoUseCase(PhotoRepository photoRepository, ThreadExecutor threadExecutor,
			PostExecutionThread postExecutionThread) {
		super(threadExecutor, postExecutionThread);
		this.photoRepository = photoRepository;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	@Override
	protected Observable buildUseCaseObservable() {
		return photoRepository.fetchPhoto(photoId);
	}
}
