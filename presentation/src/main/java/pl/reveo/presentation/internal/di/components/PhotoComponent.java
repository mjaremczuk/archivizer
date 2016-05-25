package pl.reveo.presentation.internal.di.components;

import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.internal.di.modules.ActivityModule;
import pl.reveo.presentation.internal.di.modules.PhotoModule;
import pl.reveo.presentation.view.screens.PhotoScreen;
import pl.reveo.presentation.view.screens.PhotosScreen;
import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects fetchPhoto specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PhotoModule.class})
public interface PhotoComponent extends ActivityComponent {
	void inject(PhotosScreen photosScreen);
	void inject(PhotoScreen photoScreen);
}
