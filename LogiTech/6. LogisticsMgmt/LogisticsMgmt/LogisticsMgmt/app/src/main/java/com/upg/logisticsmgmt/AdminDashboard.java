package com.upg.logisticsmgmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AdminDashboard extends AppCompatActivity implements View.OnClickListener {

    CardView vechicleBtn, bookingBtn, trackBtn, driverMgmt , viewRatingBtn;

    ImageView logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        vechicleBtn = findViewById(R.id.vechicleBtn);
        vechicleBtn.setOnClickListener(this);

        bookingBtn = findViewById(R.id.bookingBtn);
        bookingBtn.setOnClickListener(this);

        logoutBtn = findViewById(R.id.adminLogout);
        logoutBtn.setOnClickListener(this);

        trackBtn = findViewById(R.id.trackBtn);
        trackBtn.setOnClickListener(this);

        driverMgmt = findViewById(R.id.driverMgmt);
        driverMgmt.setOnClickListener(this);

        viewRatingBtn = findViewById(R.id.viewRatingBtn);
        viewRatingBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == vechicleBtn) {

            Intent intent = new Intent(getApplicationContext(),VehicleManagment.class);
            startActivity(intent);
            finish();
        }

        if (v == bookingBtn) {
            Intent intent = new Intent(getApplicationContext(),BookingActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == logoutBtn) {
            Intent intent = new Intent(getApplicationContext(),AdminLogin.class);
            startActivity(intent);
            finish();
        }

        if (v == trackBtn) {
            Intent intent = new Intent(getApplicationContext(),TrackActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == driverMgmt) {
            Intent intent = new Intent(getApplicationContext(),DriverMgmtActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == viewRatingBtn) {
            Intent intent = new Intent(getApplicationContext(),RatingListActivity.class);
            startActivity(intent);
        }
    }
}