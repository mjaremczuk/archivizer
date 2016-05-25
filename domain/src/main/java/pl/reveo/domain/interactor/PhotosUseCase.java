package pl.reveo.domain.interactor;

import pl.reveo.domain.Photo;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.repository.PhotoRepository;
import pl.reveo.domain.executor.ThreadExecutor;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link pl.reveo.domain.interactor.UseCase} that represents a use case for
 * retrieving a collection of all {@link Photo}.
 */
public class PhotosUseCase extends UseCase {

	private final PhotoRepository photoRepository;

	@Inject
	public PhotosUseCase(
			PhotoRepository photoRepository, ThreadExecutor threadExecutor,
			PostExecutionThread postExecutionThread) {
		super(threadExecutor, postExecutionThread);
		this.photoRepository = photoRepository;
	}

	@Override
	public Observable buildUseCaseObservable() {
		return photoRepository.fetchPhotos();
	}
}
