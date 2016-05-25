package pl.reveo.data.mapper;

import pl.reveo.data.entity.PhotoEntity;
import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.Photo;

/**
 * Photo mapper.
 */
public class PhotoMapper extends CommonDataMapper<Photo, PhotoEntity> {

	@Override
	public Photo instantiateObject() {
		return new Photo();
	}

	@Override
	public Photo mapValues(Photo photo, PhotoEntity photoEntity) {
      photo.setId(photoEntity.getId());
      photo.setApproved(photoEntity.getApproved());
      photo.setDeleted(photoEntity.getDeleted());
      photo.setLikes(photoEntity.getLikes());
      photo.setThumnbail(photoEntity.getThumnbail());
      photo.setFull(photoEntity.getFull());

		return photo;
	}
}
