package pl.reveo.presentation.view.screens;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.reveo.presentation.R;
import pl.reveo.presentation.model.UserModel;
import pl.reveo.presentation.view.UserView;
import pl.reveo.presentation.view.toolkit.ContainerView;
import pl.reveo.presentation.view.toolkit.Screen;

/**
 * Users screen.
 */
public class UserScreen extends Screen implements UserView {

	private static final String KEY_USER = "user_parcelable";

	Unbinder unbinder;

	@BindView(R.id.screen_user_title)
	TextView title;

	public static UserScreen newInstance(UserModel userModel) {
		final UserScreen userScreen = new UserScreen();
		Bundle bundle = new Bundle();
		bundle.putParcelable(KEY_USER, userModel);
		userScreen.setBundle(bundle);
		return userScreen;
	}

	@Override
	public void onCreateLifecycle(Context context, ContainerView parent) {
		super.onCreateLifecycle(context, parent);
		View view = LayoutInflater.from(context).inflate(R.layout.screen_user, parent);
		unbinder = ButterKnife.bind(this, view);
		UserModel userModel = getBundle().getParcelable(KEY_USER);
		title.setText(String.valueOf(System.currentTimeMillis()));
	}

	@Override
	public void onDestroyLifecycle(ContainerView parent) {
		unbinder.unbind();
		parent.removeAllViews();
		super.onDestroyLifecycle(parent);
	}

	@Override
	public void renderUser(UserModel lineup) {

	}

	@Override
	public void displayError(String message) {

	}

	@Override
	public Context getContext() {
		return null;
	}
}
