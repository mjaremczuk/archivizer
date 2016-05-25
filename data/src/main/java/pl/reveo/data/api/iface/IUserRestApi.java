package pl.reveo.data.api.iface;

import pl.reveo.data.entity.UserEntity;
import java.util.List;
import rx.Observable;

/**
 * UserRestApi for retrieving data from the network.
 */
public interface IUserRestApi {

	String API_BASE_URL = "http://remote/url/";

	String API_URL_GET_USERS = API_BASE_URL + "fetchUsers.json";

	String API_URL_GET_USER = API_BASE_URL + "user_";

	/**
	 * Retrieves an {@link rx.Observable} which will emit a List of {@link UserEntity}.
	 */
	Observable<List<UserEntity>> fetchUsers();

	/**
	 * Retrieves an {@link rx.Observable} which will emit a {@link UserEntity}.
	 *
	 * @param userId The fetchUser id used to get fetchUser data.
	 */
	Observable<UserEntity> fetchUser(final int userId);
}
