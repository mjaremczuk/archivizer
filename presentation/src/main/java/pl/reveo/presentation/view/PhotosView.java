package pl.reveo.presentation.view;

import pl.reveo.presentation.model.PhotoModel;
import java.util.Collection;
import java.util.List;

/**
 * Interface representing a View in a model view presenter pattern.
 * In this case is used as a view representing a list of {@link PhotoModel}.
 */
public interface PhotosView extends LoadDataView {
	/**
	 * Render a fetchPhoto list in the UI.
	 *
	 * @param photoModelCollection The collection of {@link PhotoModel} that will be shown.
	 */
	void renderPhotos(List<PhotoModel> photoModelCollection);
}
