package pl.reveo.presentation.mapper;

import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.Photo;
import pl.reveo.presentation.model.PhotoModel;

/**
 * Photo model mapper.
 */
public class PhotoModelMapper extends CommonDataMapper<PhotoModel, Photo> {

	@Override
	public PhotoModel instantiateObject() {
		return new PhotoModel();
	}

	@Override
	public PhotoModel mapValues(PhotoModel photoModel, Photo photo) {
		photoModel.setId(photo.getId());
		photoModel.setApproved(photo.getApproved());
		photoModel.setDeleted(photo.getDeleted());
		photoModel.setLikes(photo.getLikes());
		photoModel.setThumnbail(photo.getThumnbail());
		photoModel.setFull(photo.getFull());

		return photoModel;
	}
}
