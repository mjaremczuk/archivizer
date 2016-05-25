package pl.reveo.presentation.view.toolkit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;

/**
 * Default view holder
 */
public abstract class DefaultViewHolder<T> extends RecyclerView.ViewHolder {

	public DefaultViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public abstract void bindData(T t);
}
