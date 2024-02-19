package com.upg.logisticsmgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.upg.logisticsmgmt.adapter.LocationAdapter;
import com.upg.logisticsmgmt.adapter.VehicleAdapter;
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.util.ArrayList;

public class TrackActivity extends AppCompatActivity implements View.OnClickListener {

    ListView truckList;
    ArrayList<Vehicle> arrayList = new ArrayList<>();
    LocationAdapter adapter;
    ImageView backToDashboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        truckList = findViewById(R.id.truckList);
        backToDashboard = findViewById(R.id.backToDashboard);
        backToDashboard.setOnClickListener(this);

        Vehicle v1 = new Vehicle();
        v1.setLocation("Tumkur");
        v1.setTruckNumber("TA64727");

        Vehicle v2 = new Vehicle();
        v2.setLocation("Banglore");
        v2.setTruckNumber("KA13542");

        Vehicle v3 = new Vehicle();
        v3.setLocation("Hassan");
        v3.setTruckNumber("HY9543");

        arrayList.add(v1);
        arrayList.add(v2);
        arrayList.add(v3);
        adapter = new LocationAdapter(this, arrayList);
        truckList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == backToDashboard) {
            Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
            startActivity(intent);
            finish();
        }
    }
}