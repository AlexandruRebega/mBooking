package com.ip.alexrebega.mbooking.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ip.alexrebega.mbooking.model.Hotel;
import com.ip.alexrebega.mbooking.HotelRecyclerViewAdapter;
import com.ip.alexrebega.mbooking.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView mNoResultTextView;

    List<Hotel> dummy_hotels_dataset = new ArrayList<Hotel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        recyclerView = findViewById(R.id.recyclerView);
        mNoResultTextView = findViewById(R.id.no_result);

        // Simulate network call
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initDummyData(); // TODO replace this

        // hide "no result" text
        mNoResultTextView.setVisibility(View.GONE);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        mAdapter = new HotelRecyclerViewAdapter(dummy_hotels_dataset);
        recyclerView.setAdapter(mAdapter);
    }

    private void initDummyData() {
        Hotel h1 = new Hotel();
        h1.setmHotelName("Luxon");
        h1.setmRooms(4);
        h1.setmPrice(234.30);
        h1.setmRating(4.5f);
        h1.setImageId(R.drawable.h1_luxton);
        dummy_hotels_dataset.add(h1);

        h1 = new Hotel();
        h1.setmHotelName("ElCastillio");
        h1.setmRooms(12);
        h1.setmPrice(221.60);
        h1.setmRating(4.3f);
        h1.setImageId(R.drawable.h2_elcastillio);
        dummy_hotels_dataset.add(h1);

        h1 = new Hotel();
        h1.setmHotelName("Hard Rock Hotel");
        h1.setmRooms(6);
        h1.setmPrice(262.50);
        h1.setmRating(4f);
        h1.setImageId(R.drawable.h3_hardrock);
        dummy_hotels_dataset.add(h1);

        h1 = new Hotel();
        h1.setmHotelName("Queens");
        h1.setmRooms(9);
        h1.setmPrice(195.00);
        h1.setmRating(3.8f);
        h1.setImageId(R.drawable.h4_queens);
        dummy_hotels_dataset.add(h1);

        h1 = new Hotel();
        h1.setmHotelName("First World Hotel");
        h1.setmRooms(21);
        h1.setmPrice(194.30);
        h1.setmRating(3.7f);
        h1.setImageId(R.drawable.h5_firstworld);
        dummy_hotels_dataset.add(h1);

        h1 = new Hotel();
        h1.setmHotelName("Sunshine");
        h1.setmRooms(4);
        h1.setmPrice(219.99);
        h1.setmRating(4.7f);
        h1.setImageId(R.drawable.h6_sunshine);
        dummy_hotels_dataset.add(h1);

        h1 = new Hotel();
        h1.setmHotelName("Baglioni");
        h1.setmRooms(2);
        h1.setmPrice(204.70);
        h1.setmRating(4.2f);
        h1.setImageId(R.drawable.h7_baglioni);
        dummy_hotels_dataset.add(h1);

        h1 = new Hotel();
        h1.setmHotelName("New Pearl Hotel");
        h1.setmRooms(14);
        h1.setmPrice(289.30);
        h1.setmRating(4.8f);
        h1.setImageId(R.drawable.h8_newpearl);
        dummy_hotels_dataset.add(h1);

    }

}
