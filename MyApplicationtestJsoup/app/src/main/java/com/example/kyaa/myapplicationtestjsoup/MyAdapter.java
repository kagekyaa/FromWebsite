package com.example.kyaa.myapplicationtestjsoup;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener,
        View.OnLongClickListener{

    private ArrayList<String> mDataset;
    private static Context sContext;

    // Adapter's Constructor
    public MyAdapter(Context context, ArrayList<String> myDataset) {
        mDataset = myDataset;
        sContext = context;
    }

    // Create new views. This is invoked by the layout manager.
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // Create a new view by inflating the row item xml.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        // Set the view to the ViewHolder
        ViewHolder holder = new ViewHolder(v);
        holder.mNameTextView.setOnClickListener(MyAdapter.this);
        holder.mNameTextView.setOnLongClickListener(MyAdapter.this);

        holder.mNameTextView.setTag(holder);

        return holder;
    }

    // Replace the contents of a view. This is invoked by the layout manager.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mNumberRowTextView.setText(String.valueOf(position+1) + ". ");   //+1 so not start from 0
        // Get element from your dataset at this position and set the text for the specified element
        holder.mNameTextView.setText(mDataset.get(position));

        // Set the color to red if row is even, or to green if row is odd.
        if (position % 2 == 0) {
            holder.mNumberRowTextView.setTextColor(Color.RED);
        } else {
            holder.mNumberRowTextView.setTextColor(Color.GREEN);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Implement OnClick listener. The clicked item text is displayed in a Toast message.
    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (view.getId() == holder.mNameTextView.getId()) {
            Toast.makeText(sContext, holder.mNameTextView.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    // Implement OnLongClick listener. Long Clicked items is removed from list.
    @Override
    public boolean onLongClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        if (view.getId() == holder.mNameTextView.getId()) {
            mDataset.remove(holder.getPosition());

            // Call this method to refresh the list and display the "updated" list
            notifyDataSetChanged();

            Toast.makeText(sContext, "Item " + holder.mNameTextView.getText() + " has been removed from list",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    // Create the ViewHolder class to keep references to your views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNumberRowTextView;
        public TextView mNameTextView;

        /**
         * Constructor
         * @param v The container view which holds the elements from the row item xml
         */
        public ViewHolder(View v) {
            super(v);

            mNumberRowTextView = (TextView) v.findViewById(R.id.rowNumberTextView);
            mNameTextView = (TextView) v.findViewById(R.id.nameTextView);
        }
    }

    public class ContactInfo {
        protected String name;
        protected String surname;
        protected String email;
        protected static final String NAME_PREFIX = "Name_";
        protected static final String SURNAME_PREFIX = "Surname_";
        protected static final String EMAIL_PREFIX = "email_";
    }
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;

        public ContactViewHolder(View v) {
            super(v);
//            vName =  (TextView) v.findViewById(R.id.txtName);
//            vSurname = (TextView)  v.findViewById(R.id.txtSurname);
//            vEmail = (TextView)  v.findViewById(R.id.txtEmail);
//            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}