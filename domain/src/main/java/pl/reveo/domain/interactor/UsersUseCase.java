package pl.reveo.domain.interactor;

import pl.reveo.domain.User;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.repository.UserRepository;
import pl.reveo.domain.executor.ThreadExecutor;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link pl.reveo.domain.interactor.UseCase} that represents a use case for
 * retrieving a collection of all {@link User}.
 */
public class UsersUseCase extends UseCase {

	private final UserRepository userRepository;

	@Inject
	public UsersUseCase(
			UserRepository userRepository, ThreadExecutor threadExecutor,
			PostExecutionThread postExecutionThread) {
		super(threadExecutor, postExecutionThread);
		this.userRepository = userRepository;
	}

	@Override
	public Observable buildUseCaseObservable() {
		return userRepository.fetchUsers();
	}
}
