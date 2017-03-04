package com.damidev.dd.main.account.contacts.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.damidev.dd.R;
import com.damidev.dd.shared.dataaccess.Contact;

import java.util.ArrayList;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    private static final String TAG = "WallAdapter";

    private ArrayList<Contact> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameTextView;
        private final TextView lastNameTextView;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(, "Element " + getPosition() + " clicked.");
                    Toast.makeText(v.getContext(), getAdapterPosition() + " Clicked", Toast.LENGTH_SHORT).show();
                }
            });
            nameTextView = (TextView) v.findViewById(R.id.nameTV);
            lastNameTextView = (TextView) v.findViewById(R.id.lastNameTV);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getLastNameTextView() {
            return lastNameTextView;
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
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
