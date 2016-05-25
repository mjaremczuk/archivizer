package pl.reveo.presentation.view.screens;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.reveo.presentation.R;
import pl.reveo.presentation.model.UserModel;
import pl.reveo.presentation.presenter.UsersPresenter;
import pl.reveo.presentation.view.UsersView;
import pl.reveo.presentation.view.adapter.UsersAdapter;
import pl.reveo.presentation.view.adapter.UsersAdapter.UsersAdapterListener;
import pl.reveo.presentation.view.toolkit.ContainerView;
import pl.reveo.presentation.view.toolkit.Screen;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * Users screen.
 */
public class UsersScreen extends Screen implements UsersAdapterListener, UsersView {

	Unbinder unbinder;
	UsersAdapter usersAdapter;
	ContainerView containerView;

	@BindView(R.id.screen_users_recycler_view)
	RecyclerView recyclerView;

	@Inject
	UsersPresenter usersPresenter;

	@Override
	public void onCreateLifecycle(Context context, ContainerView parent) {
		super.onCreateLifecycle(context, parent);
		this.containerView = parent;
		View view = LayoutInflater.from(context).inflate(R.layout.screen_users, parent);
		unbinder = ButterKnife.bind(this, view);
		usersAdapter = new UsersAdapter(this);
		recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(usersAdapter);
	}

	@Override
	public void onDestroyLifecycle(ContainerView parent) {
		unbinder.unbind();
		containerView.removeAllViews();
		containerView = null;
		super.onDestroyLifecycle(parent);
	}

	@Override
	public void onClick(UserModel userModel) {
		containerView.addElement(UserScreen.newInstance(userModel));
	}

	@Override
	public void displayError(String message) {

	}

	@Override
	public Context getContext() {
		return null;
	}

	@Override
	public void renderUsers(List<UserModel> userModelCollection) {

	}
}
