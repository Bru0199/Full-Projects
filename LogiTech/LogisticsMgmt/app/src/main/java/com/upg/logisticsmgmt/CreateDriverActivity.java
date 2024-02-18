package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upg.logisticsmgmt.pojo.Driver;
import com.upg.logisticsmgmt.pojo.User;

public class CreateDriverActivity extends AppCompatActivity implements View.OnClickListener {


    EditText driverName, driverId, driverPhoneNum, driverPassword;
    Button createDriverBtn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_driver);

        driverName = findViewById(R.id.createDriverName);
        driverId = findViewById(R.id.driverId);
        driverPassword = findViewById(R.id.driverPwd);
        driverPhoneNum = findViewById(R.id.driverContactNum);


        createDriverBtn = findViewById(R.id.createDriverBtn);
        createDriverBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == createDriverBtn) {

            if (validateDriver()) {
                progressDialog = new ProgressDialog(CreateDriverActivity.this);
                progressDialog.setCancelable(false); // set cancelable to false
                progressDialog.setMessage("Please Wait"); // set message
                progressDialog.show(); // show progress dialog
                createDriver();
            } else {
                Toast.makeText(this, "Please check input fields", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean validateDriver() {

        if(driverName.getText().toString().equals("")) {
            driverName.setError("Please enter valid Driver Name");
            return false;
        }
        if(driverPhoneNum.getText().toString().equals("")) {
            driverPhoneNum.setError("Please enter valid Phone Number");
            return false;
        }
        if(driverId.getText().toString().equals("")) {
            driverId.setError("Please enter valid Driver Id");
            return false;
        }
        if(driverPassword.getText().toString().equals("")) {
            driverPassword.setError("Please enter valid Password");
            return false;
        }

        return true;
    }

    private void createDriver() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        Driver driver = new Driver();
        driver.setDriverId(driverId.getText().toString());
        driver.setDriverName(driverName.getText().toString());
        driver.setDriverPassword(driverPassword.getText().toString());
        driver.setDriverPhoneNum(driverPhoneNum.getText().toString());

        CollectionReference collectionReference = firebaseFirestore.collection("Drivers");
        collectionReference.document(driverId.getText().toString()).set(driver).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Document created/updated", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to update", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}