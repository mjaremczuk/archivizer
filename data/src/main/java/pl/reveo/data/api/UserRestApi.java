package pl.reveo.data.api;

import android.content.Context;
import pl.reveo.data.api.iface.IUserRestApi;
import pl.reveo.data.entity.UserEntity;
import pl.reveo.data.exception.NetworkConnectionException;
import pl.reveo.data.serializer.UserEntitySerializer;
import pl.reveo.data.toolkit.ApiConnection;
import java.util.List;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link UserRestApi} implementation for retrieving data from the network.
 */
public class UserRestApi implements IUserRestApi {

	private final Context context;

	/**
	 * Constructor of the class
	 *
	 * @param context {@link android.content.Context}.
	 */
	public UserRestApi(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
		}
		this.context = context.getApplicationContext();
	}

	@Override
	public Observable<List<UserEntity>> fetchUsers() {
		return Observable.create(new Observable.OnSubscribe<List<UserEntity>>() {
			@Override
			public void call(Subscriber<? super List<UserEntity>> subscriber) {
				if (ApiConnection.isThereInternetConnection(context)) {
					try {
						String responseLineupEntities =
								ApiConnection.createGET(UserRestApi.API_URL_GET_USERS).requestSyncCall();
						if (responseLineupEntities != null) {
							final UserEntitySerializer entitySerializer = new UserEntitySerializer();
							subscriber.onNext(entitySerializer.deserializeList(UserEntity.class, responseLineupEntities));
							subscriber.onCompleted();
						}
						else {
							subscriber.onError(new NetworkConnectionException());
						}
					}
					catch (Exception e) {
						subscriber.onError(new NetworkConnectionException(e.getCause()));
					}
				}
				else {
					subscriber.onError(new NetworkConnectionException());
				}
			}
		});
	}

	@Override
	public Observable<UserEntity> fetchUser(final int userId) {
		return Observable.create(new Observable.OnSubscribe<UserEntity>() {
			@Override
			public void call(Subscriber<? super UserEntity> subscriber) {
				if (ApiConnection.isThereInternetConnection(context)) {
					try {
						String response = ApiConnection.createGET(UserRestApi.API_URL_GET_USER + userId + ".json")
								.requestSyncCall();
						if (response != null) {
							final UserEntitySerializer entitySerializer = new UserEntitySerializer();
							subscriber.onNext(entitySerializer.deserialize(UserEntity.class, response));
							subscriber.onCompleted();
						}
						else {
							subscriber.onError(new NetworkConnectionException());
						}
					}
					catch (Exception e) {
						subscriber.onError(new NetworkConnectionException(e.getCause()));
					}
				}
				else {
					subscriber.onError(new NetworkConnectionException());
				}
			}
		});
	}
}
