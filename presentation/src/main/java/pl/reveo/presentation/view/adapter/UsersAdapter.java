package pl.reveo.presentation.view.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import pl.reveo.presentation.model.UserModel;
import pl.reveo.presentation.view.viewholder.UserViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Users adapter.
 */
public class UsersAdapter extends Adapter<UserViewHolder> {

	private final List<UserModel> userModelList = new ArrayList<>();
	private final UsersAdapterListener usersAdapterListener;

	public UsersAdapter(UsersAdapterListener usersAdapterListener) {
		this.usersAdapterListener = usersAdapterListener;
		userModelList.add(new UserModel());
		userModelList.add(new UserModel());
		userModelList.add(new UserModel());
	}

	@Override
	public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new UserViewHolder(usersAdapterListener, parent, LayoutInflater.from(parent.getContext()));
	}

	@Override
	public void onBindViewHolder(UserViewHolder holder, int position) {
		holder.bindData(userModelList.get(position));
	}

	@Override
	public int getItemCount() {
		return userModelList.size();
	}

	public interface UsersAdapterListener {
		void onClick(UserModel userModel);
	}
}
