package pl.reveo.presentation.view.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import pl.reveo.presentation.R;
import pl.reveo.presentation.model.UserModel;
import pl.reveo.presentation.view.adapter.UsersAdapter.UsersAdapterListener;
import pl.reveo.presentation.view.toolkit.DefaultViewHolder;

/**
 * User view holder.
 */
public class UserViewHolder extends DefaultViewHolder<UserModel> {

	@BindView(R.id.view_holder_user_title)
	TextView title;

	UsersAdapterListener usersAdapterListener;

	UserModel userModel;

	public UserViewHolder(UsersAdapterListener listener, ViewGroup viewGroup, LayoutInflater inflater) {
		super(inflater.inflate(R.layout.view_holder_user, viewGroup, false));
		this.usersAdapterListener = listener;
	}

	@Override
	public void bindData(UserModel userModel) {
		this.userModel = userModel;
//		title.setText(String.valueOf(userModel.getId()));
		title.setText(String.valueOf(System.currentTimeMillis()));
	}

	@OnClick(R.id.view_holder_user_title)
	public void onTitleClicked() {
		usersAdapterListener.onClick(userModel);
	}
}
