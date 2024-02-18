package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upg.logisticsmgmt.pojo.Booking;
import com.upg.logisticsmgmt.pojo.Driver;

public class UpdateLocationActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView driverBackBtn;
    TextView driverIdTxt;
    Button updateLocation;
    Spinner currentLoc;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location);

        driverBackBtn = findViewById(R.id.driverBackBtn);
        driverBackBtn.setOnClickListener(this);


        updateLocation = findViewById(R.id.updateLocation);
        updateLocation.setOnClickListener(this);

        driverIdTxt = findViewById(R.id.driverIdTxtField);
        driverIdTxt.setText("Driver Id : " + MainActivity.driver.getDriverId());

        currentLoc = findViewById(R.id.currentLoc);
    }

    @Override
    public void onClick(View v) {
        if (v ==  driverBackBtn) {
            Intent intent = new Intent(getApplicationContext(),DriverDashboard.class);
            startActivity(intent);
            finish();
        }

        if (v == updateLocation) {

            progressDialog = new ProgressDialog(UpdateLocationActivity.this);
            progressDialog.setCancelable(false); // set cancelable to false
            progressDialog.setMessage("Please Wait"); // set message
            progressDialog.show(); // show progress dialog
            updateCurrentLocation();
        }
    }

    private void updateCurrentLocation() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Drivers");
        String id = MainActivity.driver.getId();
        Driver d = MainActivity.driver;
        d.setCurrentLocation(currentLoc.getSelectedItem().toString());
        collectionReference.document(id).set(d).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Location Updated Successfully", Toast.LENGTH_SHORT).show();
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