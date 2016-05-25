package pl.reveo.data.api.iface;

import pl.reveo.data.entity.MeetingEntity;
import java.util.List;
import rx.Observable;

/**
 * MeetingRestApi for retrieving data from the network.
 */
public interface IMeetingRestApi {

	String API_BASE_URL = "http://remote/url/";

	String API_URL_GET_MEETINGS = API_BASE_URL + "fetchMeetings.json";

	String API_URL_GET_MEETING = API_BASE_URL + "meeting_";

	/**
	 * Retrieves an {@link rx.Observable} which will emit a List of {@link MeetingEntity}.
	 */
	Observable<List<MeetingEntity>> fetchMeetings();

	/**
	 * Retrieves an {@link rx.Observable} which will emit a {@link MeetingEntity}.
	 *
	 * @param meetingId The fetchMeeting id used to get fetchMeeting data.
	 */
	Observable<MeetingEntity> fetchMeeting(final int meetingId);
}
