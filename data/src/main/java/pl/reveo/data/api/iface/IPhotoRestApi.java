package pl.reveo.data.api.iface;

import pl.reveo.data.entity.PhotoEntity;
import java.util.List;
import rx.Observable;

/**
 * PhotoRestApi for retrieving data from the network.
 */
public interface IPhotoRestApi {

	String API_BASE_URL = "http://remote/url/";

	String API_URL_GET_PHOTOS = API_BASE_URL + "fetchPhotos.json";

	String API_URL_GET_PHOTO = API_BASE_URL + "photo_";

	/**
	 * Retrieves an {@link rx.Observable} which will emit a List of {@link PhotoEntity}.
	 */
	Observable<List<PhotoEntity>> fetchPhotos();

	/**
	 * Retrieves an {@link rx.Observable} which will emit a {@link PhotoEntity}.
	 *
	 * @param photoId The fetchPhoto id used to get fetchPhoto data.
	 */
	Observable<PhotoEntity> fetchPhoto(final int photoId);
}
