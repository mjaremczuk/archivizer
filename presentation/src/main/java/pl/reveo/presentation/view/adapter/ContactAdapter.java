package pl.reveo.presentation.view.adapter;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.reveo.presentation.R;


public class ContactAdapter extends CursorRecyclerViewAdapter<ContactAdapter.ContactViewHolder> {


    public ContactAdapter(Cursor cursor) {
        super(cursor);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder viewHolder, Cursor cursor) {
        viewHolder.bindItem(cursor);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(parent);
    }

    public String getItemLookupKey(int position){
        String lookup = null;
        if(getCursor().moveToPosition(position)){
            lookup = getCursor().getString(getCursor().getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
        }
        return lookup;
    }

    public final class ContactViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.contact_name)
        TextView textView;

        public ContactViewHolder(ViewGroup itemView) {
            super(LayoutInflater.from(itemView.getContext()).inflate(R.layout.contact_row,itemView,false));
            ButterKnife.bind(this,this.itemView);
        }

        public void bindItem(Cursor cursor){
            textView.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)));
        }
    }
}
