package pl.reveo.data.repository;

import pl.reveo.data.entity.UserEntity;
import pl.reveo.data.mapper.UserMapper;
import pl.reveo.data.repository.datasource.user.UserDataStoreFactory;
import pl.reveo.domain.User;
import pl.reveo.domain.repository.UserRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

/**
 * {@link UserRepository} for retrieving fetchUser data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

	private final UserDataStoreFactory userDataStoreFactory;

	/**
	 * Constructs a {@link UserRepository}.
	 *
	 * @param dataStoreFactory A factory to construct different data source implementations.
	 */
	@Inject
	public UserDataRepository(UserDataStoreFactory dataStoreFactory) {
		this.userDataStoreFactory = dataStoreFactory;
	}

	@Override
	public Observable<List<User>> fetchUsers() {
		return userDataStoreFactory.create().fetchUsers().map(new Func1<List<UserEntity>, List<User>>() {
			@Override
			public List<User> call(List<UserEntity> userEntities) {
				final UserMapper userMapper = new UserMapper();
				return userMapper.transform(userEntities);
			}
		});
	}

	@Override
	public Observable<User> fetchUser(int lineupId) {
		return userDataStoreFactory.create().fetchUser(lineupId).map(new Func1<UserEntity, User>() {
			@Override
			public User call(UserEntity userEntity) {
				final UserMapper userMapper = new UserMapper();
				return userMapper.transform(userEntity);
			}
		});
	}
}
