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
import pl.reveo.presentation.model.MeetingModel;
import pl.reveo.presentation.presenter.MeetingsPresenter;
import pl.reveo.presentation.view.MeetingsView;
import pl.reveo.presentation.view.adapter.MeetingsAdapter;
import pl.reveo.presentation.view.adapter.MeetingsAdapter.MeetingsAdapterListener;
import pl.reveo.presentation.view.toolkit.ContainerView;
import pl.reveo.presentation.view.toolkit.Screen;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * Meetings screen.
 */
public class MeetingsScreen extends Screen implements MeetingsAdapterListener, MeetingsView {

	Unbinder unbinder;
	MeetingsAdapter meetingsAdapter;
	ContainerView containerView;

	@BindView(R.id.screen_meetings_recycler_view)
	RecyclerView recyclerView;

	@Inject
	MeetingsPresenter meetingsPresenter;

	@Override
	public void onCreateLifecycle(Context context, ContainerView parent) {
		super.onCreateLifecycle(context, parent);
		this.containerView = parent;
		View view = LayoutInflater.from(context).inflate(R.layout.screen_meetings, parent);
		unbinder = ButterKnife.bind(this, view);
		meetingsAdapter = new MeetingsAdapter(this);
		recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(meetingsAdapter);
	}

	@Override
	public void onDestroyLifecycle(ContainerView parent) {
		unbinder.unbind();
		containerView.removeAllViews();
		containerView = null;
		super.onDestroyLifecycle(parent);
	}

	@Override
	public void onClick(MeetingModel meetingModel) {
		containerView.addElement(MeetingScreen.newInstance(meetingModel));
	}

	@Override
	public void displayError(String message) {

	}

	@Override
	public Context getContext() {
		return null;
	}

	@Override
	public void renderMeetings(List<MeetingModel> meetingModelCollection) {

	}
}
