package pl.reveo.data.repository;

import pl.reveo.data.entity.MeetingEntity;
import pl.reveo.data.mapper.MeetingMapper;
import pl.reveo.data.repository.datasource.meeting.MeetingDataStoreFactory;
import pl.reveo.domain.Meeting;
import pl.reveo.domain.repository.MeetingRepository;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.Observable;
import rx.functions.Func1;

/**
 * {@link MeetingRepository} for retrieving fetchMeeting data.
 */
@Singleton
public class MeetingDataRepository implements MeetingRepository {

	private final MeetingDataStoreFactory meetingDataStoreFactory;

	/**
	 * Constructs a {@link MeetingRepository}.
	 *
	 * @param dataStoreFactory A factory to construct different data source implementations.
	 */
	@Inject
	public MeetingDataRepository(MeetingDataStoreFactory dataStoreFactory) {
		this.meetingDataStoreFactory = dataStoreFactory;
	}

	@Override
	public Observable<List<Meeting>> fetchMeetings() {
		return meetingDataStoreFactory.create().fetchMeetings().map(new Func1<List<MeetingEntity>, List<Meeting>>() {
			@Override
			public List<Meeting> call(List<MeetingEntity> meetingEntities) {
				final MeetingMapper meetingMapper = new MeetingMapper();
				return meetingMapper.transform(meetingEntities);
			}
		});
	}

	@Override
	public Observable<Meeting> fetchMeeting(int lineupId) {
		return meetingDataStoreFactory.create().fetchMeeting(lineupId).map(new Func1<MeetingEntity, Meeting>() {
			@Override
			public Meeting call(MeetingEntity meetingEntity) {
				final MeetingMapper meetingMapper = new MeetingMapper();
				return meetingMapper.transform(meetingEntity);
			}
		});
	}
}
