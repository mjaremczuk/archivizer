package pl.reveo.data.toolkit;

import android.content.Context;
import pl.reveo.data.exception.NetworkConnectionException;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

/**
 * Wrapper to {@link OnSubscribe}
 *
 * @param <T>
 */
public abstract class WrapperOnSubscribe<T> implements OnSubscribe<T> {

	private Context context;

	public WrapperOnSubscribe(Context context) {
		this.context = context;
	}

	@Override
	public void call(Subscriber<? super T> subscriber) {
		if (ApiConnection.isThereInternetConnection(context)) {
			try {
				ResponseErrorWrapper response = makeCallAndGetResponse();
				if (response != null) {
					if (response.exception() == null) {
						String body = response.body();
//						Timber.d("Response %s", body);
						responseSuccessful(subscriber, body);
					}
					else {
						subscriber.onError(response.exception());
					}
				}
				else {
					subscriber.onError(new NetworkConnectionException());
				}

			}
			catch (Exception e) {
				subscriber.onError(e);
			}
		}
		else {
			subscriber.onError(new NetworkConnectionException());
		}

		context = null;
	}

	public abstract ResponseErrorWrapper makeCallAndGetResponse() throws Exception;

	public abstract void responseSuccessful(Subscriber<? super T> subscriber, String response);


}
