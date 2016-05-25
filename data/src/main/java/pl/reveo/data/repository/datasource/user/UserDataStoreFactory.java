package pl.reveo.data.repository.datasource.user;

import android.content.Context;
import pl.reveo.data.api.UserRestApi;
import pl.reveo.data.cache.UserCache;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link IUserDataStore}.
 */
@Singleton
public class UserDataStoreFactory {

	private final Context context;
	private final UserCache userCache;

	@Inject
	public UserDataStoreFactory(Context context, UserCache userCache) {
		if (context == null || userCache == null) {
			throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
		}
		this.context = context.getApplicationContext();
		this.userCache = userCache;
	}

	/**
	 * Create
	 */
	public IUserDataStore create() {
		final UserRestApi restApi = new UserRestApi(context);
		return new ApiUserDataStore(restApi, userCache);
	}
}
