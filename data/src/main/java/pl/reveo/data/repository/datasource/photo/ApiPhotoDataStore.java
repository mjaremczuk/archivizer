package pl.reveo.data.repository.datasource.photo;

import pl.reveo.data.api.PhotoRestApi;
import pl.reveo.data.cache.PhotoCache;
import pl.reveo.data.entity.PhotoEntity;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IPhotoDataStore} implementation based on connections to the api (Cloud).
 */
public class ApiPhotoDataStore implements IPhotoDataStore {

	private final PhotoRestApi restApi;
	private PhotoCache photoCache;

	private final Action1<PhotoEntity> saveToCacheAction =
			new Action1<PhotoEntity>() {
				@Override
				public void call(PhotoEntity photoEntity) {
					//todo
				}
			};

	/**
	 * Construct a {@link IPhotoDataStore} based on connections to the api (Cloud).
	 *
	 * @param restApi The {@link PhotoRestApi} implementation to use.
	 * @param photoCache A {@link PhotoCache} to cache data retrieved from the api.
	 */
	public ApiPhotoDataStore(PhotoRestApi restApi, PhotoCache photoCache) {
		this.restApi = restApi;
		this.photoCache = photoCache;
	}

	@Override
	public Observable<List<PhotoEntity>> fetchPhotos() {
		return restApi.fetchPhotos();
	}

	@Override
	public Observable<PhotoEntity> fetchPhoto(final int photoId) {
		return restApi.fetchPhoto(photoId).doOnNext(saveToCacheAction);
	}
}
