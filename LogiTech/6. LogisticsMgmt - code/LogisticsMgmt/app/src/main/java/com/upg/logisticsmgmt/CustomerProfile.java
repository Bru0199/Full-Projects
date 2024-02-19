package com.upg.logisticsmgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.upg.logisticsmgmt.pojo.User;

public class CustomerProfile extends AppCompatActivity implements View.OnClickListener {

    ImageView userBackBtn;
    Button userLogout;
    TextView profieUserName,profileContactNum,profileEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        User u = MainActivity.user;


        profieUserName = findViewById(R.id.profieUserName);
        profieUserName.setText("Name :" + u.getName());

        profileContactNum = findViewById(R.id.profileContactNum);
        profileContactNum.setText("Contact Number :" + u.getContactNum());

        profileEmailId = findViewById(R.id.profileEmailId);
        profileEmailId.setText("Email Id  :" + u.getEmail());


        userBackBtn = findViewById(R.id.userBackBtn);
        userBackBtn.setOnClickListener(this);

        userLogout = findViewById(R.id.userLogout);
        userLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == userBackBtn) {
            Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
            startActivity(intent);
            finish();
        }

        if(v == userLogout) {
            Intent intent = new Intent(getApplicationContext(),CustomerRegistration.class);
            startActivity(intent);
            finish();
        }
    }
}