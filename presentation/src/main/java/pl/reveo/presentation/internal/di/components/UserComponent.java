package pl.reveo.presentation.internal.di.components;

import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.internal.di.modules.ActivityModule;
import pl.reveo.presentation.internal.di.modules.UserModule;
import pl.reveo.presentation.view.screens.UserScreen;
import pl.reveo.presentation.view.screens.UsersScreen;
import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects fetchUser specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface UserComponent extends ActivityComponent {
	void inject(UsersScreen usersScreen);
	void inject(UserScreen userScreen);
}
