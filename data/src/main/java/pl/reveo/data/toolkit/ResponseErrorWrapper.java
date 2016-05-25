package pl.reveo.data.toolkit;

import com.squareup.okhttp.Response;
import java.io.IOException;

public class ResponseErrorWrapper {

	Exception exception;
	String responseBody;
	Response response;

	public ResponseErrorWrapper() {
	}

	public ResponseErrorWrapper(Response response) {
		this.response = response;
	}

	public void validateResponse(Response response) throws IOException {
		this.response = response;
		verifyResponseQuality();
	}

	void verifyResponseQuality() throws IOException {
		if (response.code() == 200) {
			responseBody = response.body().string();
		}
		else if (response.code() == 500) {
			String responseE = response.body().string();
			responseBody = null;
			exception = new Exception();
		}
		else if (response.code() == 401) {
			String responseB = response.body().string();
//				ErrorResponseSerializer errorResponseSerializer = new ErrorResponseSerializer();
//				ErrorResponse errorResponse = errorResponseSerializer.deserialize(ErrorResponse.class,responseB);
//				exception = new AccountNotVerifiedException(errorResponse.getError_description());
		}
		else if (response.code() == 400) {
			responseBody = response.body().string();
//				ErrorResponseSerializer errorResponseSerializer = new ErrorResponseSerializer();
//				ErrorResponse errorResponse = errorResponseSerializer.deserialize(ErrorResponse.class,responseBody);
//				exception = new LogInErrorException(errorResponse.getError_description());
		}
		response.body().close();
	}

	public String body() {
		return responseBody;
	}

	public Exception exception() {
		return exception;
	}

	public void setErrorException(Exception errorException) {
		this.exception = errorException;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
}
