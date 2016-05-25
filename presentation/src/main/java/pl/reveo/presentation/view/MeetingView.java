package pl.reveo.presentation.view;

import pl.reveo.presentation.model.MeetingModel;

/**
 * Interface representing a View in a model view presenter pattern.
 * In this case is used as a view representing a fetchMeeting profile.
 */
public interface MeetingView extends LoadDataView {
	/**
	 * Render a fetchMeeting in the UI.
	 *
	 * @param meetingModel The {@link MeetingModel} that will be shown.
	 */
	void renderMeeting(MeetingModel meetingModel);
}
