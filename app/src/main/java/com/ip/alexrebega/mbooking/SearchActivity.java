package com.ip.alexrebega.mbooking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class SearchActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final LatLngBounds LAT_LNG_BOUNDS =  new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));

    private Context mContext;
    private AutoCompleteTextView mSearchView;
    private EditText mFromEditText;
    private EditText mUntilEditText;
    private EditText mHowManyEditText;
    private Button   mSearchButton;

    private GoogleApiClient mGoogleApiClient;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mContext = getApplicationContext();
        FloatingActionButton openSettings = (FloatingActionButton) findViewById(R.id.settingsBtn);
        openSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, SettingsActivity.class);
                startActivity(i);
            }
        });

        mSearchView = findViewById(R.id.simpleSearchView);
        mFromEditText = findViewById(R.id.fromEditText);
        mUntilEditText = findViewById(R.id.untilEditText);
        mHowManyEditText = findViewById(R.id.howManyEditText);
        mSearchButton = findViewById(R.id.searchBtn);

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, LAT_LNG_BOUNDS ,null);
        mSearchView.setAdapter(mPlaceAutocompleteAdapter);
    }


}
