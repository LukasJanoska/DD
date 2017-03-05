package com.damidev.dd.main.account.contacts.ui;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damidev.dd.R;
import com.damidev.dd.main.account.editcontact.ui.EditContactFragment;
import com.damidev.dd.shared.dataaccess.Contact;

import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private static final String TAG = "WallAdapter";

    private ArrayList<Contact> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final TextView lastNameTextView;
        private final TextView emailTextView;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(, "Element " + getPosition() + " clicked.");
                    replaceWithEditContactFragment(v.getContext(), getAdapterPosition());
                }
            });
            nameTextView = (TextView) v.findViewById(R.id.nameTV);
            lastNameTextView = (TextView) v.findViewById(R.id.lastNameTV);
            emailTextView = (TextView) v.findViewById(R.id.emailTV);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getLastNameTextView() {
            return lastNameTextView;
        }//

        public TextView getEmailTextView() {
            return emailTextView;
        }

        public void replaceWithEditContactFragment(Context context, int contact_id) {
            FragmentTransaction ft = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
            EditContactFragment fragment = EditContactFragment.newInstance(EditContactFragment.EditContactFragmnetTag, contact_id);
            ft.replace(R.id.fragment_main_container, fragment);
            ft.commit();
        }
    }

    public ContactAdapter(ArrayList<Contact> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.contact_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getNameTextView().setText(mDataSet.get(position).getName());
        viewHolder.getLastNameTextView().setText(mDataSet.get(position).getLastname());
        viewHolder.getEmailTextView().setText(mDataSet.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
