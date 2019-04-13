package com.ip.alexrebega.mbooking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.Calendar;

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
    private myDateWatcher tw1;
    private myDateWatcher tw2;

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
        // get suggestions
        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, LAT_LNG_BOUNDS ,null);
        mSearchView.setAdapter(mPlaceAutocompleteAdapter);

        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSearch();
            }
        });

        // check date format
        tw1 = new myDateWatcher(mFromEditText);
        tw2 = new myDateWatcher(mUntilEditText);
        mFromEditText.addTextChangedListener(tw1);
        mUntilEditText.addTextChangedListener(tw2);

    }

    private void launchSearch() {

        mSearchView.setError(null);
        mFromEditText.setError(null);
        mUntilEditText.setError(null);
        mHowManyEditText.setError(null);

        boolean cancel = false;
        View focusView = null;

        if(mSearchView.getText().toString().isEmpty()) {
            cancel = true;
            focusView = mSearchView;
            mSearchView.setError("Enter a location");
        }

        String fromDate = mFromEditText.getText().toString();
        String untilDate = mUntilEditText.getText().toString();

        if(fromDate.isEmpty()) {
            cancel = true;
            focusView = mFromEditText;
            mFromEditText.setError("Enter the departure date");
        }

        if(untilDate.isEmpty()) {
            cancel = true;
            focusView = mUntilEditText;
            mUntilEditText.setError("Please select a date");
        }

        // compare dates
        //TODO

        String howMany = mHowManyEditText.getText().toString();
        if(howMany.isEmpty()) {
            cancel = true;
            focusView = mHowManyEditText;
            mHowManyEditText.setError("How many travellers?");
        }

        if(cancel){
            focusView.requestFocus();
        } else {
            Intent i = new Intent(this, SearchResultActivity.class);
            startActivity(i);
        }

    }


    private class myDateWatcher implements TextWatcher {
        private String current = "";
        private String ddmmyyyy = "DDMMYYYY";
        private Calendar cal = Calendar.getInstance();
        private EditText date;

        myDateWatcher(EditText dateInpt) {
            date = dateInpt;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().equals(current)) {
                String clean = s.toString().replaceAll("[^\\d.]", "");
                String cleanC = current.replaceAll("[^\\d.]", "");

                int cl = clean.length();
                int sel = cl;
                for (int i = 2; i <= cl && i < 6; i += 2) {
                    sel++;
                }
                //Fix for pressing delete next to a forward slash
                if (clean.equals(cleanC)) sel--;

                if (clean.length() < 8){
                    clean = clean + ddmmyyyy.substring(clean.length());
                }else{
                    //This part makes sure that when we finish entering numbers
                    //the date is correct, fixing it otherwise
                    int day  = Integer.parseInt(clean.substring(0,2));
                    int mon  = Integer.parseInt(clean.substring(2,4));
                    int year = Integer.parseInt(clean.substring(4,8));

                    if(mon > 12) mon = 12;
                    cal.set(Calendar.MONTH, mon-1);
                    year = (year<1900)?1900:(year>2100)?2100:year;
                    cal.set(Calendar.YEAR, year);
                    // ^ first set year for the line below to work correctly
                    //with leap years - otherwise, date e.g. 29/02/2012
                    //would be automatically corrected to 28/02/2012

                    day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                    clean = String.format("%02d%02d%02d",day, mon, year);
                }

                clean = String.format("%s/%s/%s", clean.substring(0, 2),
                        clean.substring(2, 4),
                        clean.substring(4, 8));

                sel = sel < 0 ? 0 : sel;
                current = clean;
                date.setText(current);
                date.setSelection(sel < current.length() ? sel : current.length());
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void afterTextChanged(Editable s) {}
    };



}
