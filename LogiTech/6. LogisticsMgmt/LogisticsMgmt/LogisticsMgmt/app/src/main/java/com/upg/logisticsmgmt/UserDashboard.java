package com.upg.logisticsmgmt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class UserDashboard extends AppCompatActivity implements View.OnClickListener {


    CardView payBtn, bookingBtn, trackBtn, ratingBtn;

    ImageView profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);
        payBtn = findViewById(R.id.payBtn);
        payBtn.setOnClickListener(this);

        bookingBtn = findViewById(R.id.customerBookingBtn);
        bookingBtn.setOnClickListener(this);

        profileBtn = findViewById(R.id.profile);
        profileBtn.setOnClickListener(this);

        trackBtn = findViewById(R.id.customerTrackBtn);
        trackBtn.setOnClickListener(this);

        ratingBtn = findViewById(R.id.ratingBtn);
        ratingBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == payBtn) {

            Intent intent = new Intent(getApplicationContext(),BankDetailActivity.class);
            startActivity(intent);
//            finish();
        }

        if (v == bookingBtn) {
            Intent intent = new Intent(getApplicationContext(),BookingDetailActivity.class);
            startActivity(intent);
//            finish();
        }

        if (v == profileBtn) {
            Intent intent = new Intent(getApplicationContext(),CustomerProfile.class);
            startActivity(intent);
            finish();
        }

        if (v == trackBtn) {
            Intent intent = new Intent(getApplicationContext(),UserTrackActivity.class);
            startActivity(intent);
//            finish();
        }

        if (v == ratingBtn) {
            Intent intent = new Intent(getApplicationContext(),UserRatingActivity.class);
            startActivity(intent);
        }
    }
}