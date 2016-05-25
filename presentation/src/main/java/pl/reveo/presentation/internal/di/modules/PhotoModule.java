package pl.reveo.presentation.internal.di.modules;

import pl.reveo.domain.interactor.PhotoUseCase;
import pl.reveo.domain.interactor.PhotosUseCase;
import pl.reveo.domain.interactor.UseCase;
import pl.reveo.presentation.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Dagger module that provides fetchPhoto related collaborators.
 */
@Module
public class PhotoModule {

	public PhotoModule() {
	}

	@Provides
	@PerActivity
	@Named("photos")
	UseCase providePhotosUseCase(PhotosUseCase photosUseCase) {
		return photosUseCase;
	}

	@Provides
	@PerActivity
	@Named("photo")
	UseCase providePhotoUseCase(PhotoUseCase photoUseCase) {
		return photoUseCase;
	}
}