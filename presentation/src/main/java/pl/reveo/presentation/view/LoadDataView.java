package pl.reveo.presentation.view;

import android.content.Context;

/**
 * Interface representing a View that will use to load data.
 */
public interface LoadDataView {
	/**
	 * Displays error
	 *
	 * @param message Error message.
	 */
	void displayError(String message);

	/**
	 * @return context
	 */
	Context getContext();
}
