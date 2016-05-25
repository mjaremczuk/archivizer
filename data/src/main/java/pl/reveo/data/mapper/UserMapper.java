package pl.reveo.data.mapper;

import pl.reveo.data.entity.UserEntity;
import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.User;

/**
 * User mapper.
 */
public class UserMapper extends CommonDataMapper<User, UserEntity> {

	@Override
	public User instantiateObject() {
		return new User();
	}

	@Override
	public User mapValues(User user, UserEntity userEntity) {
      user.setId(userEntity.getId());
      user.setAccessToken(userEntity.getAccessToken());
      user.setEmail(userEntity.getEmail());
      user.setName(userEntity.getName());
      user.setPhoneNumber(userEntity.getPhoneNumber());
      user.setTicket(userEntity.getTicket());

		return user;
	}
}
