package com.ip.alexrebega.mbooking.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ip.alexrebega.mbooking.BookingApp;
import com.ip.alexrebega.mbooking.model.Hotel;
import com.ip.alexrebega.mbooking.R;

public class HotelViewActivity extends AppCompatActivity {

    private Hotel hotel;
    private TextView mDescTextView;
    private ImageView mImageView;
    private RatingBar mHotelRating;
    private TextView mHotelRooms;
    private TextView mHotelPrice;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);

        hotel = getIntent().getParcelableExtra("hotelKey");
        toolbarLayout.setTitle(hotel.getmHotelName());

        mDescTextView = findViewById(R.id.hotel_desc_text);
        mDescTextView.setText(hotel.getmHotelDesc());

        mImageView = findViewById(R.id.imageView);
        mImageView.setImageResource(hotel.getImageId());

        mHotelRating = findViewById(R.id.hotel_rating_bar);
        mHotelRating.setRating(hotel.getmRating());

        mHotelRooms = findViewById(R.id.roomsTextView);
        mHotelRooms.append(Integer.toString(hotel.getmRooms()));

        mHotelPrice = findViewById(R.id.priceTextView);
        mHotelPrice.append(Double.toString(hotel.getmPrice()));

        mButton = findViewById(R.id.bookButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnack();
            }
        });
    }

    private void showSnack(){
        Snackbar.make(mButton, "Reservation complete!", Snackbar.LENGTH_SHORT)
                .show();
    }
}
