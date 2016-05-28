package pl.reveo.data.mapper;

import pl.reveo.data.entity.MeetingEntity;
import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.Meeting;

/**
 * Meeting mapper.
 */
public class MeetingMapper extends CommonDataMapper<Meeting, MeetingEntity> {

	@Override
	public Meeting instantiateObject() {
		return new Meeting();
	}

	@Override
	public Meeting mapValues(Meeting meeting, MeetingEntity meetingEntity) {
      meeting.setId(meetingEntity.getId());
      meeting.setName(meetingEntity.getName());
      meeting.setLat(meetingEntity.getLat());
      meeting.setLng(meetingEntity.getLng());
//      meeting.setUsers(meetingEntity.getUsers());
      meeting.setMeetingTime(meetingEntity.getMeetingTime());

		return meeting;
	}
}
