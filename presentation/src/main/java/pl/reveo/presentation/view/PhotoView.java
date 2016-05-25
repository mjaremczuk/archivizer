package pl.reveo.presentation.view;

import pl.reveo.presentation.model.PhotoModel;

/**
 * Interface representing a View in a model view presenter pattern.
 * In this case is used as a view representing a fetchPhoto profile.
 */
public interface PhotoView extends LoadDataView {
	/**
	 * Render a fetchPhoto in the UI.
	 *
	 * @param photoModel The {@link PhotoModel} that will be shown.
	 */
	void renderPhoto(PhotoModel photoModel);
}
