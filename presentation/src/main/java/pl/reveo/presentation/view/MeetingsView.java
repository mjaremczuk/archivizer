package pl.reveo.presentation.view;

import pl.reveo.presentation.model.MeetingModel;
import java.util.Collection;
import java.util.List;

/**
 * Interface representing a View in a model view presenter pattern.
 * In this case is used as a view representing a list of {@link MeetingModel}.
 */
public interface MeetingsView extends LoadDataView {
	/**
	 * Render a fetchMeeting list in the UI.
	 *
	 * @param meetingModelCollection The collection of {@link MeetingModel} that will be shown.
	 */
	void renderMeetings(List<MeetingModel> meetingModelCollection);
}
