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
import pl.reveo.presentation.view.UserView;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link Presenter} that controls communication between views and models of the presentation
 * layer.
 */
@PerActivity
public class UserPresenter implements Presenter {

	private UserView viewDetailsView;

	private final UseCase userUseCase;

	@Inject
	public UserPresenter(@Named("user") UseCase userUseCase) {
		this.userUseCase = userUseCase;
	}

	public void setView(@NonNull UserView view) {
		viewDetailsView = view;
	}

	@Override
	public void resume() {
	}

	@Override
	public void destroy() {
		userUseCase.unsubscribe();
	}

	/**
	 * Initializes the presenter by start retrieving fetchUser details.
	 */
	public void initialize(int userId) {
		userUseCase.execute(new LineupDetailsSubscriber());
	}

	private void showErrorMessage(ErrorBundle errorBundle) {
		String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.getContext(), errorBundle.getException());
		viewDetailsView.displayError(errorMessage);
	}

	private void showUserDetailsInView(User user) {
		final UserModelMapper userModelMapper = new UserModelMapper();
		final UserModel userModel = userModelMapper.transform(user);
		viewDetailsView.renderUser(userModel);
	}

	private final class LineupDetailsSubscriber extends DefaultSubscriber<User> {

		@Override
		public void onCompleted() {
			//todo UI
		}

		@Override
		public void onError(Throwable e) {
			//todo UI
		}

		@Override
		public void onNext(User user) {
			showUserDetailsInView(user);
		}
	}
}
