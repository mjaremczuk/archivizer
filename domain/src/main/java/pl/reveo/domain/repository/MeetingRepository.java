package pl.reveo.domain.repository;

import pl.reveo.domain.Meeting;
import java.util.List;
import rx.Observable;

/**
 * Interface that represents a Repository for getting {@link Meeting} related data.
 */
public interface MeetingRepository {
	/**
	 * Get an {@link rx.Observable} which will emit a List of {@link Meeting}.
	 */
	Observable<List<Meeting>> fetchMeetings();

	/**
	 * Get an {@link rx.Observable} which will emit a {@link Meeting}.
	 *
	 * @param meetingId The fetchMeeting id used to retrieve fetchMeeting data.
	 */
	Observable<Meeting> fetchMeeting(final int meetingId);
}
