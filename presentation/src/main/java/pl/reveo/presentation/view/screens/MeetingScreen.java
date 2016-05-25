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
import pl.reveo.presentation.model.MeetingModel;
import pl.reveo.presentation.view.MeetingView;
import pl.reveo.presentation.view.toolkit.ContainerView;
import pl.reveo.presentation.view.toolkit.Screen;

/**
 * Meetings screen.
 */
public class MeetingScreen extends Screen implements MeetingView {

	private static final String KEY_MEETING = "meeting_parcelable";

	Unbinder unbinder;

	@BindView(R.id.screen_meeting_title)
	TextView title;

	public static MeetingScreen newInstance(MeetingModel meetingModel) {
		final MeetingScreen meetingScreen = new MeetingScreen();
		Bundle bundle = new Bundle();
		bundle.putParcelable(KEY_MEETING, meetingModel);
		meetingScreen.setBundle(bundle);
		return meetingScreen;
	}

	@Override
	public void onCreateLifecycle(Context context, ContainerView parent) {
		super.onCreateLifecycle(context, parent);
		View view = LayoutInflater.from(context).inflate(R.layout.screen_meeting, parent);
		unbinder = ButterKnife.bind(this, view);
		MeetingModel meetingModel = getBundle().getParcelable(KEY_MEETING);
		title.setText(String.valueOf(System.currentTimeMillis()));
	}

	@Override
	public void onDestroyLifecycle(ContainerView parent) {
		unbinder.unbind();
		parent.removeAllViews();
		super.onDestroyLifecycle(parent);
	}

	@Override
	public void renderMeeting(MeetingModel lineup) {

	}

	@Override
	public void displayError(String message) {

	}

	@Override
	public Context getContext() {
		return null;
	}
}
