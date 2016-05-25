package pl.reveo.data.repository.datasource.photo;

import pl.reveo.data.entity.PhotoEntity;
import java.util.List;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface IPhotoDataStore {
	/**
	 * Get an {@link rx.Observable} which will emit a List of {@link PhotoEntity}.
	 */
	Observable<List<PhotoEntity>> fetchPhotos();

	/**
	 * Get an {@link rx.Observable} which will emit a {@link PhotoEntity} by its id.
	 *
	 * @param lineupId The id to retrieve fetchPhoto data.
	 */
	Observable<PhotoEntity> fetchPhoto(final int lineupId);
}
