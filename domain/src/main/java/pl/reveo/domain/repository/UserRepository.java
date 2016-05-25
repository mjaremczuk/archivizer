package pl.reveo.domain.repository;

import pl.reveo.domain.User;
import java.util.List;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link User} related data.
 */
public interface UserRepository {
	/**
	 * Get an {@link rx.Observable} which will emit a List of {@link User}.
	 */
	Observable<List<User>> fetchUsers();

	/**
	 * Get an {@link rx.Observable} which will emit a {@link User}.
	 *
	 * @param userId The fetchUser id used to retrieve fetchUser data.
	 */
	Observable<User> fetchUser(final int userId);
}
