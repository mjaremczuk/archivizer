package pl.reveo.data.api;

import android.content.Context;
import pl.reveo.data.api.iface.IMeetingRestApi;
import pl.reveo.data.entity.MeetingEntity;
import pl.reveo.data.exception.NetworkConnectionException;
import pl.reveo.data.serializer.MeetingEntitySerializer;
import pl.reveo.data.toolkit.ApiConnection;
import java.util.List;
import rx.Observable;
import rx.Subscriber;

/**
 * {@link MeetingRestApi} implementation for retrieving data from the network.
 */
public class MeetingRestApi implements IMeetingRestApi {

	private final Context context;

	/**
	 * Constructor of the class
	 *
	 * @param context {@link android.content.Context}.
	 */
	public MeetingRestApi(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
		}
		this.context = context.getApplicationContext();
	}

	@Override
	public Observable<List<MeetingEntity>> fetchMeetings() {
		return Observable.create(new Observable.OnSubscribe<List<MeetingEntity>>() {
			@Override
			public void call(Subscriber<? super List<MeetingEntity>> subscriber) {
				if (ApiConnection.isThereInternetConnection(context)) {
					try {
						String responseLineupEntities =
								ApiConnection.createGET(MeetingRestApi.API_URL_GET_MEETINGS).requestSyncCall();
						if (responseLineupEntities != null) {
							final MeetingEntitySerializer entitySerializer = new MeetingEntitySerializer();
							subscriber.onNext(entitySerializer.deserializeList(MeetingEntity.class, responseLineupEntities));
							subscriber.onCompleted();
						}
						else {
							subscriber.onError(new NetworkConnectionException());
						}
					}
					catch (Exception e) {
						subscriber.onError(new NetworkConnectionException(e.getCause()));
					}
				}
				else {
					subscriber.onError(new NetworkConnectionException());
				}
			}
		});
	}

	@Override
	public Observable<MeetingEntity> fetchMeeting(final int meetingId) {
		return Observable.create(new Observable.OnSubscribe<MeetingEntity>() {
			@Override
			public void call(Subscriber<? super MeetingEntity> subscriber) {
				if (ApiConnection.isThereInternetConnection(context)) {
					try {
						String response = ApiConnection.createGET(MeetingRestApi.API_URL_GET_MEETING + meetingId + ".json")
								.requestSyncCall();
						if (response != null) {
							final MeetingEntitySerializer entitySerializer = new MeetingEntitySerializer();
							subscriber.onNext(entitySerializer.deserialize(MeetingEntity.class, response));
							subscriber.onCompleted();
						}
						else {
							subscriber.onError(new NetworkConnectionException());
						}
					}
					catch (Exception e) {
						subscriber.onError(new NetworkConnectionException(e.getCause()));
					}
				}
				else {
					subscriber.onError(new NetworkConnectionException());
				}
			}
		});
	}
}
