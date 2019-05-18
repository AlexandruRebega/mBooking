package com.ip.alexrebega.mbooking;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ip.alexrebega.mbooking.activity.HotelViewActivity;
import com.ip.alexrebega.mbooking.model.HotelDto;

import java.util.List;

public class HotelRecyclerViewAdapter extends RecyclerView.Adapter<HotelRecyclerViewAdapter.MyViewHolder> {
    private List<HotelDto> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView hImage;
        public TextView hName;
        public TextView hRooms;
        public TextView hPrice;
        public RatingBar hRating;


        public MyViewHolder(ConstraintLayout v) {
            super(v);
            hImage = v.findViewById(R.id.hotelImageView);
            hName = v.findViewById(R.id.hotelNameTextView);
            hRooms = v.findViewById(R.id.hotelRoomsTextView);
            hPrice = v.findViewById(R.id.priceTextView);
            hRating = v.findViewById(R.id.ratingBar);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); // gets item position

                    HotelDto h = getAdapterData(position);
                    if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                        Intent i = new Intent(v.getContext(), HotelViewActivity.class);
                        i.putExtra("hotelKey", h);
                        v.getContext().startActivity(i);
                    }
                }
            });
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HotelRecyclerViewAdapter(List<HotelDto> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HotelRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hotel, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.setText(mDataset.get(position));
        holder.hName.setText(mDataset.get(position).getmHotelName());
        holder.hRooms.append(Integer.toString(mDataset.get(position).getmRooms()));
        holder.hPrice.append(Double.toString(mDataset.get(position).getmPrice()));
        holder.hRating.setRating(mDataset.get(position).getmRating());
        holder.hImage.setImageResource(mDataset.get(position).getImageId());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public HotelDto getAdapterData(int position) {
        return mDataset.get(position);
    }
}
