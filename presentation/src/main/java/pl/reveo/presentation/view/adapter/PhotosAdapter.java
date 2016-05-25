package pl.reveo.presentation.view.adapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import pl.reveo.presentation.model.PhotoModel;
import pl.reveo.presentation.view.viewholder.PhotoViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * Photos adapter.
 */
public class PhotosAdapter extends Adapter<PhotoViewHolder> {

	private final List<PhotoModel> photoModelList = new ArrayList<>();
	private final PhotosAdapterListener photosAdapterListener;

	public PhotosAdapter(PhotosAdapterListener photosAdapterListener) {
		this.photosAdapterListener = photosAdapterListener;
		photoModelList.add(new PhotoModel());
		photoModelList.add(new PhotoModel());
		photoModelList.add(new PhotoModel());
	}

	@Override
	public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new PhotoViewHolder(photosAdapterListener, parent, LayoutInflater.from(parent.getContext()));
	}

	@Override
	public void onBindViewHolder(PhotoViewHolder holder, int position) {
		holder.bindData(photoModelList.get(position));
	}

	@Override
	public int getItemCount() {
		return photoModelList.size();
	}

	public interface PhotosAdapterListener {
		void onClick(PhotoModel photoModel);
	}
}
