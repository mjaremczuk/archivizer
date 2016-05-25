package pl.reveo.data.mapper;

import pl.reveo.data.entity.PhotoEntity;
import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.Photo;

/**
 * Mapper class used to transform {@link PhotoEntity} (in the data layer) to {@link Photo} in the
 * domain layer.
 */
public class PhotoEntityMapper extends CommonDataMapper<PhotoEntity, Photo> {

	@Override
	public PhotoEntity instantiateObject() {
		return new PhotoEntity();
	}

	@Override
	public PhotoEntity mapValues(PhotoEntity photoEntity, Photo photo) {
		      photoEntity.setId(photo.getId());
      photoEntity.setApproved(photo.getApproved());
      photoEntity.setDeleted(photo.getDeleted());
      photoEntity.setLikes(photo.getLikes());
      photoEntity.setThumnbail(photo.getThumnbail());
      photoEntity.setFull(photo.getFull());

		return photoEntity;
	}
}
