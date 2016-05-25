package pl.reveo.data.repository;

import pl.reveo.data.entity.PhotoEntity;
import pl.reveo.data.mapper.PhotoMapper;
import pl.reveo.data.repository.datasource.photo.PhotoDataStoreFactory;
import pl.reveo.domain.Photo;
import pl.reveo.domain.repository.PhotoRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

/**
 * {@link PhotoRepository} for retrieving fetchPhoto data.
 */
@Singleton
public class PhotoDataRepository implements PhotoRepository {

	private final PhotoDataStoreFactory photoDataStoreFactory;

	/**
	 * Constructs a {@link PhotoRepository}.
	 *
	 * @param dataStoreFactory A factory to construct different data source implementations.
	 */
	@Inject
	public PhotoDataRepository(PhotoDataStoreFactory dataStoreFactory) {
		this.photoDataStoreFactory = dataStoreFactory;
	}

	@Override
	public Observable<List<Photo>> fetchPhotos() {
		return photoDataStoreFactory.create().fetchPhotos().map(new Func1<List<PhotoEntity>, List<Photo>>() {
			@Override
			public List<Photo> call(List<PhotoEntity> photoEntities) {
				final PhotoMapper photoMapper = new PhotoMapper();
				return photoMapper.transform(photoEntities);
			}
		});
	}

	@Override
	public Observable<Photo> fetchPhoto(int lineupId) {
		return photoDataStoreFactory.create().fetchPhoto(lineupId).map(new Func1<PhotoEntity, Photo>() {
			@Override
			public Photo call(PhotoEntity photoEntity) {
				final PhotoMapper photoMapper = new PhotoMapper();
				return photoMapper.transform(photoEntity);
			}
		});
	}
}
