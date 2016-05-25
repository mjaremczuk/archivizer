package pl.reveo.data.repository.datasource.meeting;

import pl.reveo.data.entity.MeetingEntity;
import java.util.List;
import rx.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface IMeetingDataStore {
	/**
	 * Get an {@link rx.Observable} which will emit a List of {@link MeetingEntity}.
	 */
	Observable<List<MeetingEntity>> fetchMeetings();

	/**
	 * Get an {@link rx.Observable} which will emit a {@link MeetingEntity} by its id.
	 *
	 * @param lineupId The id to retrieve fetchMeeting data.
	 */
	Observable<MeetingEntity> fetchMeeting(final int lineupId);
}
