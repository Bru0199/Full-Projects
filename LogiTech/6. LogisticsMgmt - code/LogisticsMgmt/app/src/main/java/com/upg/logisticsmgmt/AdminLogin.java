 package com.upg.logisticsmgmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity implements View.OnClickListener {

    Button goBtn;
    EditText name, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        goBtn = findViewById(R.id.adminLogin);
        goBtn.setOnClickListener(this);

        name= findViewById(R.id.adminName);
        password= findViewById(R.id.password);

    }

    @Override
    public void onClick(View v) {
        if (v == goBtn) {
            if (validate()) {
                Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Please check credentials", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validate() {
        if(name.getText().toString().equals("") || !name.getText().toString().equals("UP2G69")) {
            name.setError("Please enter valid Name");
            return false;
        }
        if(password.getText().toString().equals("") || !password.getText().toString().equals("admin123")) {
            password.setError("Please enter valid Password");
            return false;
        }

        return true;
    }
}