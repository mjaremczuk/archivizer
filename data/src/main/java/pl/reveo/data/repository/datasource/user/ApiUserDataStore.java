package pl.reveo.data.repository.datasource.user;

import pl.reveo.data.api.UserRestApi;
import pl.reveo.data.cache.UserCache;
import pl.reveo.data.entity.UserEntity;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IUserDataStore} implementation based on connections to the api (Cloud).
 */
public class ApiUserDataStore implements IUserDataStore {

	private final UserRestApi restApi;
	private UserCache userCache;

	private final Action1<UserEntity> saveToCacheAction =
			new Action1<UserEntity>() {
				@Override
				public void call(UserEntity userEntity) {
					//todo
				}
			};

	/**
	 * Construct a {@link IUserDataStore} based on connections to the api (Cloud).
	 *
	 * @param restApi The {@link UserRestApi} implementation to use.
	 * @param userCache A {@link UserCache} to cache data retrieved from the api.
	 */
	public ApiUserDataStore(UserRestApi restApi, UserCache userCache) {
		this.restApi = restApi;
		this.userCache = userCache;
	}

	@Override
	public Observable<List<UserEntity>> fetchUsers() {
		return restApi.fetchUsers();
	}

	@Override
	public Observable<UserEntity> fetchUser(final int userId) {
		return restApi.fetchUser(userId).doOnNext(saveToCacheAction);
	}
}
