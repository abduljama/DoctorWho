package com.abduljama.doctorwho;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class DoctorProfileActivity extends AppCompatActivity   implements OnMapReadyCallback, LocationListener {

    GoogleMap map;
    Location myLocation;
    ActionBar actionBar;
    Button btn_book;
    String  doctors_name;
    String  hospital;

    TextView txtDocName;
    TextView txtHosp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        txtDocName = (TextView)findViewById(R.id.txtDocName);
        txtHosp  = (TextView)findViewById(R.id.txtHosp);


//
        Intent in = getIntent();

        Bundle bundle = in.getExtras();
        if ( !bundle.equals(null)) {
             doctors_name = in.getStringExtra("Name");
            hospital = in.getStringExtra("Hospital");
        }

        txtDocName.setText(doctors_name);
        txtHosp.setText(hospital);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btn_book = (Button)findViewById(R.id.btn_book);

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent in = new Intent(getApplication(), MainActivity.class);
                startActivity(in);
                return true;
        }
        return (super.onOptionsItemSelected(item));}
}

