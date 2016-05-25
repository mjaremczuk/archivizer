package pl.reveo.data.repository.datasource.photo;

import android.content.Context;
import pl.reveo.data.api.PhotoRestApi;
import pl.reveo.data.cache.PhotoCache;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link IPhotoDataStore}.
 */
@Singleton
public class PhotoDataStoreFactory {

	private final Context context;
	private final PhotoCache photoCache;

	@Inject
	public PhotoDataStoreFactory(Context context, PhotoCache photoCache) {
		if (context == null || photoCache == null) {
			throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
		}
		this.context = context.getApplicationContext();
		this.photoCache = photoCache;
	}

	/**
	 * Create
	 */
	public IPhotoDataStore create() {
		final PhotoRestApi restApi = new PhotoRestApi(context);
		return new ApiPhotoDataStore(restApi, photoCache);
	}
}
