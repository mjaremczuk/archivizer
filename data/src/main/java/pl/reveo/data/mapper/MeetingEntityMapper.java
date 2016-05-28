package pl.reveo.data.mapper;

import pl.reveo.data.entity.MeetingEntity;
import pl.reveo.data.toolkit.CommonDataMapper;
import pl.reveo.domain.Meeting;

/**
 * Mapper class used to transform {@link MeetingEntity} (in the data layer) to {@link Meeting} in the
 * domain layer.
 */
public class MeetingEntityMapper extends CommonDataMapper<MeetingEntity, Meeting> {

	@Override
	public MeetingEntity instantiateObject() {
		return new MeetingEntity();
	}

	@Override
	public MeetingEntity mapValues(MeetingEntity meetingEntity, Meeting meeting) {
		      meetingEntity.setId(meeting.getId());
      meetingEntity.setName(meeting.getName());
      meetingEntity.setLat(meeting.getLat());
      meetingEntity.setLng(meeting.getLng());
//      meetingEntity.setUsers(meeting.getUsers());
      meetingEntity.setMeetingTime(meeting.getMeetingTime());

		return meetingEntity;
	}
}
