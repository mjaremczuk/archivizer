package pl.reveo.data.toolkit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Api Connection.
 */
public class ApiConnection implements Callable<String> {

	/**
	 * Content-Type header.
	 */
	private static final String CONTENT_TYPE_LABEL = "Content-Type";
	private static final String CONTENT_TYPE_VALUE = "application/json; charset=utf-8";

	/**
	 * Accept header.
	 */
	private static final String ACCEPT_LABEL = "Accept";
	private static final String ACCEPT_VALUE = "application/json";

	private URL url;
	private String response;

	private ApiConnection(String url) throws MalformedURLException {
		this.url = new URL(url);
	}

	public static ApiConnection createGET(String url) throws MalformedURLException {
		return new ApiConnection(url);
	}

	/**
	 * Do a request to an api synchronously.
	 * It should not be executed in the main thread of the application.
	 *
	 * @return A string response
	 */
	@Nullable
	public String requestSyncCall() {
		connectToApi();
		return response;
	}

	private void connectToApi() {
		OkHttpClient okHttpClient = this.createClient();
		final Request request = new Request.Builder()
				.url(this.url)
				.addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE)
				.addHeader(ACCEPT_LABEL, ACCEPT_VALUE)
				.get()
				.build();

		try {
			this.response = okHttpClient.newCall(request).execute().body().string();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private OkHttpClient createClient() {
		final OkHttpClient okHttpClient = new OkHttpClient();
		okHttpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
		okHttpClient.setConnectTimeout(15000, TimeUnit.MILLISECONDS);
		return okHttpClient;
	}

	@Override
	public String call() throws Exception {
		return requestSyncCall();
	}

	/**
	 * Checks if the device has any active internet connection.
	 *
	 * @return true device with internet connection, otherwise false.
	 */
	public static boolean isThereInternetConnection(Context context) {
		boolean isConnected;
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());
		return isConnected;
	}
}
