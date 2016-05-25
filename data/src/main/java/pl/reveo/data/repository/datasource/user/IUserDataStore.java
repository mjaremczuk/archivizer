package pl.reveo.data.repository.datasource.user;

import pl.reveo.data.entity.UserEntity;
import java.util.List;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface IUserDataStore {
	/**
	 * Get an {@link rx.Observable} which will emit a List of {@link UserEntity}.
	 */
	Observable<List<UserEntity>> fetchUsers();

	/**
	 * Get an {@link rx.Observable} which will emit a {@link UserEntity} by its id.
	 *
	 * @param lineupId The id to retrieve fetchUser data.
	 */
	Observable<UserEntity> fetchUser(final int lineupId);
}
