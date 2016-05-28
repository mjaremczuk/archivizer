package pl.reveo.presentation.mapper;

import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.Meeting;
import pl.reveo.presentation.model.MeetingModel;

/**
 * Meeting model mapper.
 */
public class MeetingModelMapper extends CommonDataMapper<MeetingModel, Meeting> {

	@Override
	public MeetingModel instantiateObject() {
		return new MeetingModel();
	}

	@Override
	public MeetingModel mapValues(MeetingModel meetingModel, Meeting meeting) {
		meetingModel.setId(meeting.getId());
		meetingModel.setName(meeting.getName());
		meetingModel.setLat(meeting.getLat());
		meetingModel.setLng(meeting.getLng());
//		meetingModel.setUsers(meeting.getUsers());
		meetingModel.setMeetingTime(meeting.getMeetingTime());

		return meetingModel;
	}
}
