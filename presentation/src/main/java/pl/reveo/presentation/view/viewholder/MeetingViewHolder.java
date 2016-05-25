package pl.reveo.presentation.view.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import pl.reveo.presentation.R;
import pl.reveo.presentation.model.MeetingModel;
import pl.reveo.presentation.view.adapter.MeetingsAdapter.MeetingsAdapterListener;
import pl.reveo.presentation.view.toolkit.DefaultViewHolder;

/**
 * Meeting view holder.
 */
public class MeetingViewHolder extends DefaultViewHolder<MeetingModel> {

	@BindView(R.id.view_holder_meeting_title)
	TextView title;

	MeetingsAdapterListener meetingsAdapterListener;

	MeetingModel meetingModel;

	public MeetingViewHolder(MeetingsAdapterListener listener, ViewGroup viewGroup, LayoutInflater inflater) {
		super(inflater.inflate(R.layout.view_holder_meeting, viewGroup, false));
		this.meetingsAdapterListener = listener;
	}

	@Override
	public void bindData(MeetingModel meetingModel) {
		this.meetingModel = meetingModel;
//		title.setText(String.valueOf(meetingModel.getId()));
		title.setText(String.valueOf(System.currentTimeMillis()));
	}

	@OnClick(R.id.view_holder_meeting_title)
	public void onTitleClicked() {
		meetingsAdapterListener.onClick(meetingModel);
	}
}
