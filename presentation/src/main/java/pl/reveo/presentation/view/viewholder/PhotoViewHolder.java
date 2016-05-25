package pl.reveo.presentation.view.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import pl.reveo.presentation.R;
import pl.reveo.presentation.model.PhotoModel;
import pl.reveo.presentation.view.adapter.PhotosAdapter.PhotosAdapterListener;
import pl.reveo.presentation.view.toolkit.DefaultViewHolder;

/**
 * Photo view holder.
 */
public class PhotoViewHolder extends DefaultViewHolder<PhotoModel> {

	@BindView(R.id.view_holder_photo_title)
	TextView title;

	PhotosAdapterListener photosAdapterListener;

	PhotoModel photoModel;

	public PhotoViewHolder(PhotosAdapterListener listener, ViewGroup viewGroup, LayoutInflater inflater) {
		super(inflater.inflate(R.layout.view_holder_photo, viewGroup, false));
		this.photosAdapterListener = listener;
	}

	@Override
	public void bindData(PhotoModel photoModel) {
		this.photoModel = photoModel;
//		title.setText(String.valueOf(photoModel.getId()));
		title.setText(String.valueOf(System.currentTimeMillis()));
	}

	@OnClick(R.id.view_holder_photo_title)
	public void onTitleClicked() {
		photosAdapterListener.onClick(photoModel);
	}
}
