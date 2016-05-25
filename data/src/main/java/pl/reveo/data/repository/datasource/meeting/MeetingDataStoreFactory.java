package pl.reveo.data.repository.datasource.meeting;

import android.content.Context;
import pl.reveo.data.api.MeetingRestApi;
import pl.reveo.data.cache.MeetingCache;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Factory that creates different implementations of {@link IMeetingDataStore}.
 */
@Singleton
public class MeetingDataStoreFactory {

	private final Context context;
	private final MeetingCache meetingCache;

	@Inject
	public MeetingDataStoreFactory(Context context, MeetingCache meetingCache) {
		if (context == null || meetingCache == null) {
			throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
		}
		this.context = context.getApplicationContext();
		this.meetingCache = meetingCache;
	}

	/**
	 * Create
	 */
	public IMeetingDataStore create() {
		final MeetingRestApi restApi = new MeetingRestApi(context);
		return new ApiMeetingDataStore(restApi, meetingCache);
	}
}
