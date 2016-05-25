package pl.reveo.presentation.view.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import pl.reveo.presentation.model.MeetingModel;
import pl.reveo.presentation.view.viewholder.MeetingViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Meetings adapter.
 */
public class MeetingsAdapter extends Adapter<MeetingViewHolder> {

	private final List<MeetingModel> meetingModelList = new ArrayList<>();
	private final MeetingsAdapterListener meetingsAdapterListener;

	public MeetingsAdapter(MeetingsAdapterListener meetingsAdapterListener) {
		this.meetingsAdapterListener = meetingsAdapterListener;
		meetingModelList.add(new MeetingModel());
		meetingModelList.add(new MeetingModel());
		meetingModelList.add(new MeetingModel());
	}

	@Override
	public MeetingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new MeetingViewHolder(meetingsAdapterListener, parent, LayoutInflater.from(parent.getContext()));
	}

	@Override
	public void onBindViewHolder(MeetingViewHolder holder, int position) {
		holder.bindData(meetingModelList.get(position));
	}

	@Override
	public int getItemCount() {
		return meetingModelList.size();
	}

	public interface MeetingsAdapterListener {
		void onClick(MeetingModel meetingModel);
	}
}
