package pl.reveo.presentation.internal.di.modules;

import pl.reveo.domain.interactor.UserUseCase;
import pl.reveo.domain.interactor.UsersUseCase;
import pl.reveo.domain.interactor.UseCase;
import pl.reveo.presentation.internal.di.PerActivity;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Dagger module that provides fetchUser related collaborators.
 */
@Module
public class UserModule {

	public UserModule() {
	}

	@Provides
	@PerActivity
	@Named("users")
	UseCase provideUsersUseCase(UsersUseCase usersUseCase) {
		return usersUseCase;
	}

	@Provides
	@PerActivity
	@Named("user")
	UseCase provideUserUseCase(UserUseCase userUseCase) {
		return userUseCase;
	}
}