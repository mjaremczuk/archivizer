package pl.reveo.presentation.view.screens;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.reveo.presentation.R;
import pl.reveo.presentation.model.PhotoModel;
import pl.reveo.presentation.presenter.PhotosPresenter;
import pl.reveo.presentation.view.PhotosView;
import pl.reveo.presentation.view.adapter.PhotosAdapter;
import pl.reveo.presentation.view.adapter.PhotosAdapter.PhotosAdapterListener;
import pl.reveo.presentation.view.toolkit.ContainerView;
import pl.reveo.presentation.view.toolkit.Screen;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

/**
 * Photos screen.
 */
public class PhotosScreen extends Screen implements PhotosAdapterListener, PhotosView {

	Unbinder unbinder;
	PhotosAdapter photosAdapter;
	ContainerView containerView;

	@BindView(R.id.screen_photos_recycler_view)
	RecyclerView recyclerView;

	@Inject
	PhotosPresenter photosPresenter;

	@Override
	public void onCreateLifecycle(Context context, ContainerView parent) {
		super.onCreateLifecycle(context, parent);
		this.containerView = parent;
		View view = LayoutInflater.from(context).inflate(R.layout.screen_photos, parent);
		unbinder = ButterKnife.bind(this, view);
		photosAdapter = new PhotosAdapter(this);
		recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(photosAdapter);
	}

	@Override
	public void onDestroyLifecycle(ContainerView parent) {
		unbinder.unbind();
		containerView.removeAllViews();
		containerView = null;
		super.onDestroyLifecycle(parent);
	}

	@Override
	public void onClick(PhotoModel photoModel) {
		containerView.addElement(PhotoScreen.newInstance(photoModel));
	}

	@Override
	public void displayError(String message) {

	}

	@Override
	public Context getContext() {
		return null;
	}

	@Override
	public void renderPhotos(List<PhotoModel> photoModelCollection) {

	}
}
