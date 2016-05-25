package pl.reveo.domain.interactor;

import pl.reveo.domain.User;
import pl.reveo.domain.executor.PostExecutionThread;
import pl.reveo.domain.executor.ThreadExecutor;
import pl.reveo.domain.repository.UserRepository;
import javax.inject.Inject;
import rx.Observable;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving data related to an specific {@link User}.
 */
public class UserUseCase extends UseCase {

	private final UserRepository userRepository;

	private int userId;

	@Inject
	public UserUseCase(UserRepository userRepository, ThreadExecutor threadExecutor,
			PostExecutionThread postExecutionThread) {
		super(threadExecutor, postExecutionThread);
		this.userRepository = userRepository;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	protected Observable buildUseCaseObservable() {
		return userRepository.fetchUser(userId);
	}
}
