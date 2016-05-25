package pl.reveo.data.repository.datasource.meeting;

import pl.reveo.data.api.MeetingRestApi;
import pl.reveo.data.cache.MeetingCache;
import pl.reveo.data.entity.MeetingEntity;
import java.util.List;
import rx.Observable;
import rx.functions.Action1;

/**
 * {@link IMeetingDataStore} implementation based on connections to the api (Cloud).
 */
public class ApiMeetingDataStore implements IMeetingDataStore {

	private final MeetingRestApi restApi;
	private MeetingCache meetingCache;

	private final Action1<MeetingEntity> saveToCacheAction =
			new Action1<MeetingEntity>() {
				@Override
				public void call(MeetingEntity meetingEntity) {
					//todo
				}
			};

	/**
	 * Construct a {@link IMeetingDataStore} based on connections to the api (Cloud).
	 *
	 * @param restApi The {@link MeetingRestApi} implementation to use.
	 * @param meetingCache A {@link MeetingCache} to cache data retrieved from the api.
	 */
	public ApiMeetingDataStore(MeetingRestApi restApi, MeetingCache meetingCache) {
		this.restApi = restApi;
		this.meetingCache = meetingCache;
	}

	@Override
	public Observable<List<MeetingEntity>> fetchMeetings() {
		return restApi.fetchMeetings();
	}

	@Override
	public Observable<MeetingEntity> fetchMeeting(final int meetingId) {
		return restApi.fetchMeeting(meetingId).doOnNext(saveToCacheAction);
	}
}
