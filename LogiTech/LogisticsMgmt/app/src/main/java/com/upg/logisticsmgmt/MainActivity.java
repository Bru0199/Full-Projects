package com.upg.logisticsmgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.upg.logisticsmgmt.pojo.Booking;
import com.upg.logisticsmgmt.pojo.Driver;
import com.upg.logisticsmgmt.pojo.User;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button adminBtn;
    Button driverBtn;
    Button userBtn;

    public  static User user;
    public  static Booking booking;
    public  static Driver driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminBtn = findViewById(R.id.button1);
        adminBtn.setOnClickListener(this);

        driverBtn = findViewById(R.id.button2);
        driverBtn.setOnClickListener(this);

        userBtn = findViewById(R.id.button3);
        userBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v== adminBtn) {
            Intent intent = new Intent(getApplicationContext(),AdminLogin.class);
            startActivity(intent);
            finish();

        }

        if (v == driverBtn) {
            Intent intent = new Intent(getApplicationContext(),DriverLoginActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == userBtn) {
            Intent intent = new Intent(getApplicationContext(),CustomerRegistration.class);
            startActivity(intent);
            finish();

        }
    }
}