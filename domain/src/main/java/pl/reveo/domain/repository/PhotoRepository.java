package pl.reveo.domain.repository;

import pl.reveo.domain.Photo;
import java.util.List;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Photo} related data.
 */
public interface PhotoRepository {
	/**
	 * Get an {@link rx.Observable} which will emit a List of {@link Photo}.
	 */
	Observable<List<Photo>> fetchPhotos();

	/**
	 * Get an {@link rx.Observable} which will emit a {@link Photo}.
	 *
	 * @param photoId The fetchPhoto id used to retrieve fetchPhoto data.
	 */
	Observable<Photo> fetchPhoto(final int photoId);
}
