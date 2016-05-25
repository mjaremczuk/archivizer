package pl.reveo.data.mapper;

import pl.reveo.data.entity.UserEntity;
import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.User;

/**
 * Mapper class used to transform {@link UserEntity} (in the data layer) to {@link User} in the
 * domain layer.
 */
public class UserEntityMapper extends CommonDataMapper<UserEntity, User> {

	@Override
	public UserEntity instantiateObject() {
		return new UserEntity();
	}

	@Override
	public UserEntity mapValues(UserEntity userEntity, User user) {
		      userEntity.setId(user.getId());
      userEntity.setAccessToken(user.getAccessToken());
      userEntity.setEmail(user.getEmail());
      userEntity.setName(user.getName());
      userEntity.setPhoneNumber(user.getPhoneNumber());
      userEntity.setTicket(user.getTicket());

		return userEntity;
	}
}
