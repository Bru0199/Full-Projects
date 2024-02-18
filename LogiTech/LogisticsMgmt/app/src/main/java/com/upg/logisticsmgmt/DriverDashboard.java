package com.upg.logisticsmgmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DriverDashboard extends AppCompatActivity implements View.OnClickListener {


    CardView  bookingBtn, trackBtn;
    ImageView logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_dashboard);

        bookingBtn = findViewById(R.id.driverBookingBtn);
        bookingBtn.setOnClickListener(this);


        trackBtn = findViewById(R.id.locationUpdateBtn);
        trackBtn.setOnClickListener(this);

        logoutBtn = findViewById(R.id.driverLogout);
        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bookingBtn) {
            Intent intent = new Intent(getApplicationContext(),OrderListActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == logoutBtn) {
            Intent intent = new Intent(getApplicationContext(),DriverLoginActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == trackBtn) {
            Intent intent = new Intent(getApplicationContext(),UpdateLocationActivity.class);
            startActivity(intent);
            finish();
        }
    }
}