package pl.reveo.presentation.mapper;

import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.User;
import pl.reveo.presentation.model.UserModel;

/**
 * User model mapper.
 */
public class UserModelMapper extends CommonDataMapper<UserModel, User> {

	@Override
	public UserModel instantiateObject() {
		return new UserModel();
	}

	@Override
	public UserModel mapValues(UserModel userModel, User user) {
		userModel.setId(user.getId());
		userModel.setAccessToken(user.getAccessToken());
		userModel.setEmail(user.getEmail());
		userModel.setName(user.getName());
		userModel.setPhoneNumber(user.getPhoneNumber());
		userModel.setTicket(user.getTicket());

		return userModel;
	}
}
