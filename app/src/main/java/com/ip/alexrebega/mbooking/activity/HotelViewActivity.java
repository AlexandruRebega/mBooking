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

import com.ip.alexrebega.mbooking.db.AppDatabase;
import com.ip.alexrebega.mbooking.model.Hotel;
import com.ip.alexrebega.mbooking.model.HotelDto;
import com.ip.alexrebega.mbooking.R;
import com.ip.alexrebega.mbooking.model.Reservation;
import com.ip.alexrebega.mbooking.model.User;

import java.util.List;

public class HotelViewActivity extends AppCompatActivity {

    private HotelDto hotel;
    private TextView mDescTextView;
    private ImageView mImageView;
    private RatingBar mHotelRating;
    private TextView mHotelRooms;
    private TextView mHotelPrice;
    private Button mButton;

    private int travelers;

    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);

        hotel = getIntent().getParcelableExtra("hotelKey");
        toolbarLayout.setTitle(hotel.getmHotelName());
        travelers = getIntent().getIntExtra("travelersKey", 0);

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
        database = AppDatabase.getAppDatabase(getApplicationContext());
        List<User> users = database.userDao().getAll();

        Reservation reservation = new Reservation();
        reservation.travelers = travelers;

        final Hotel hotelEnt = new Hotel();
        hotelEnt.imageId = hotel.getImageId();
        hotelEnt.mHotelName = hotel.getmHotelName();
        hotelEnt.mPrice = hotel.getmPrice();
        hotelEnt.mRating = hotel.getmRating();
        hotelEnt.mRooms = hotel.getmRooms();

        new Thread(new Runnable() {
            @Override
            public void run() {
                database.hotelDao().insert(hotelEnt);
            }
        }) .start();

        if(users!= null && users.size() != 0) {
            Snackbar.make(mButton, "Reservation complete :"+ users.get(0).mail, Snackbar.LENGTH_SHORT)
                    .show();

        }
        else{
            Snackbar.make(mButton, "Reservation complete!", Snackbar.LENGTH_SHORT)
                    .show();

        }
    }
}
