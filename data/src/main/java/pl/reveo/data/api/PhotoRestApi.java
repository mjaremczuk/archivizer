package pl.reveo.data.api;

import android.content.Context;
import pl.reveo.data.api.iface.IPhotoRestApi;
import pl.reveo.data.entity.PhotoEntity;
import pl.reveo.data.exception.NetworkConnectionException;
import pl.reveo.data.serializer.PhotoEntitySerializer;
import pl.reveo.data.toolkit.ApiConnection;
import java.util.List;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link PhotoRestApi} implementation for retrieving data from the network.
 */
public class PhotoRestApi implements IPhotoRestApi {

	private final Context context;

	/**
	 * Constructor of the class
	 *
	 * @param context {@link android.content.Context}.
	 */
	public PhotoRestApi(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
		}
		this.context = context.getApplicationContext();
	}

	@Override
	public Observable<List<PhotoEntity>> fetchPhotos() {
		return Observable.create(new Observable.OnSubscribe<List<PhotoEntity>>() {
			@Override
			public void call(Subscriber<? super List<PhotoEntity>> subscriber) {
				if (ApiConnection.isThereInternetConnection(context)) {
					try {
						String responseLineupEntities =
								ApiConnection.createGET(PhotoRestApi.API_URL_GET_PHOTOS).requestSyncCall();
						if (responseLineupEntities != null) {
							final PhotoEntitySerializer entitySerializer = new PhotoEntitySerializer();
							subscriber.onNext(entitySerializer.deserializeList(PhotoEntity.class, responseLineupEntities));
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
	public Observable<PhotoEntity> fetchPhoto(final int photoId) {
		return Observable.create(new Observable.OnSubscribe<PhotoEntity>() {
			@Override
			public void call(Subscriber<? super PhotoEntity> subscriber) {
				if (ApiConnection.isThereInternetConnection(context)) {
					try {
						String response = ApiConnection.createGET(PhotoRestApi.API_URL_GET_PHOTO + photoId + ".json")
								.requestSyncCall();
						if (response != null) {
							final PhotoEntitySerializer entitySerializer = new PhotoEntitySerializer();
							subscriber.onNext(entitySerializer.deserialize(PhotoEntity.class, response));
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
