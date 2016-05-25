package pl.reveo.presentation.view;

import pl.reveo.presentation.model.UserModel;
import java.util.Collection;
import java.util.List;

/**
 * Interface representing a View in a model view presenter pattern.
 * In this case is used as a view representing a list of {@link UserModel}.
 */
public interface UsersView extends LoadDataView {
	/**
	 * Render a fetchUser list in the UI.
	 *
	 * @param userModelCollection The collection of {@link UserModel} that will be shown.
	 */
	void renderUsers(List<UserModel> userModelCollection);
}
