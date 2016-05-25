package pl.reveo.presentation.view;

import pl.reveo.presentation.model.UserModel;

/**
 * Interface representing a View in a model view presenter pattern.
 * In this case is used as a view representing a fetchUser profile.
 */
public interface UserView extends LoadDataView {
	/**
	 * Render a fetchUser in the UI.
	 *
	 * @param userModel The {@link UserModel} that will be shown.
	 */
	void renderUser(UserModel userModel);
}
