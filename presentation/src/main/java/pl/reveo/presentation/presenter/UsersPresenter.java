package pl.reveo.presentation.presenter;

import android.support.annotation.NonNull;
import pl.reveo.domain.User;
import pl.reveo.domain.exception.ErrorBundle;
import pl.reveo.domain.interactor.DefaultSubscriber;
import pl.reveo.domain.interactor.UseCase;
import pl.reveo.presentation.exception.ErrorMessageFactory;
import pl.reveo.presentation.internal.di.PerActivity;
import pl.reveo.presentation.mapper.UserModelMapper;
import pl.reveo.presentation.model.UserModel;
import pl.reveo.presentation.view.UsersView;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UsersPresenter extends DefaultSubscriber<List<User>> implements Presenter {

	private UsersView viewListView;
	private final UseCase usersUseCase;

	@Inject
	public UsersPresenter(@Named("users") UseCase usersUseCase) {
		this.usersUseCase = usersUseCase;
	}

	public void setView(@NonNull UsersView view) {
		viewListView = view;
	}

	@Override
	public void resume() {
	}

	@Override
	public void destroy() {
		usersUseCase.unsubscribe();
	}

	/**
	 * Initializes the presenter by start retrieving the fetchUser list.
	 */
	public void initialize() {
		usersUseCase.execute(new LineupListSubscriber());
	}

	private void showErrorMessage(ErrorBundle errorBundle) {
		String errorMessage = ErrorMessageFactory.create(this.viewListView.getContext(), errorBundle.getException());
		viewListView.displayError(errorMessage);
	}

	private void showLineupsCollectionInView(List<User> usersCollection) {
		final UserModelMapper userModelMapper = new UserModelMapper();
		final List<UserModel> userModelCollection = userModelMapper.transform(usersCollection);
		viewListView.renderUsers(userModelCollection);
	}

	private final class LineupListSubscriber extends DefaultSubscriber<List<User>> {

		@Override
		public void onCompleted() {
			//todo UI hide loader
		}

		@Override
		public void onError(Throwable e) {
			//todo UI show error
		}

		@Override
		public void onNext(List<User> users) {
			//todo display data
			showLineupsCollectionInView(users);
		}
	}
}
