package pl.reveo.presentation.view.screens;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.reveo.presentation.R;
import pl.reveo.presentation.model.PhotoModel;
import pl.reveo.presentation.view.PhotoView;
import pl.reveo.presentation.view.toolkit.ContainerView;
import pl.reveo.presentation.view.toolkit.Screen;

/**
 * Photos screen.
 */
public class PhotoScreen extends Screen implements PhotoView {

	private static final String KEY_PHOTO = "photo_parcelable";

	Unbinder unbinder;

	@BindView(R.id.screen_photo_title)
	TextView title;

	public static PhotoScreen newInstance(PhotoModel photoModel) {
		final PhotoScreen photoScreen = new PhotoScreen();
		Bundle bundle = new Bundle();
		bundle.putParcelable(KEY_PHOTO, photoModel);
		photoScreen.setBundle(bundle);
		return photoScreen;
	}

	@Override
	public void onCreateLifecycle(Context context, ContainerView parent) {
		super.onCreateLifecycle(context, parent);
		View view = LayoutInflater.from(context).inflate(R.layout.screen_photo, parent);
		unbinder = ButterKnife.bind(this, view);
		PhotoModel photoModel = getBundle().getParcelable(KEY_PHOTO);
		title.setText(String.valueOf(System.currentTimeMillis()));
	}

	@Override
	public void onDestroyLifecycle(ContainerView parent) {
		unbinder.unbind();
		parent.removeAllViews();
		super.onDestroyLifecycle(parent);
	}

	@Override
	public void renderPhoto(PhotoModel lineup) {

	}

	@Override
	public void displayError(String message) {

	}

	@Override
	public Context getContext() {
		return null;
	}
}
