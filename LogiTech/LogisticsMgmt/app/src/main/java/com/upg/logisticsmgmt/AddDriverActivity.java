package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.upg.logisticsmgmt.pojo.Booking;
import com.upg.logisticsmgmt.pojo.Driver;

import java.util.ArrayList;

public class AddDriverActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView backToBooking;
    Spinner driverSelectSpinner;
    ArrayList<String> driverIdList;
    ArrayList<Driver> driverList;
    ProgressDialog progressDialog;
    Button assignDriver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);
        driverIdList = new ArrayList<String>();
        driverList = new ArrayList<Driver>();

        backToBooking = findViewById(R.id.backToBooking);
        backToBooking.setOnClickListener(this);

        assignDriver = findViewById(R.id.assignDriver);
        assignDriver.setOnClickListener(this);

        driverSelectSpinner = findViewById(R.id.driverSelectSpinner);
        progressDialog = new ProgressDialog(AddDriverActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        getAllDrivers();

    }

    @Override
    public void onClick(View v) {
        if (v == backToBooking) {
            Intent intent = new Intent(getApplicationContext(),BookingActivity.class);
            startActivity(intent);
            finish();
        }

        if (v == assignDriver) {
            updateDriverForBooking();
        }
    }

    private void getAllDrivers() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Drivers");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        driverIdList.add(document.getId());
                        Driver d = document.toObject(Driver.class);
                        driverList.add(d);
                    }

                    String[] driverArray = new String[driverIdList.size()];
                    driverArray = driverIdList.toArray(driverArray);
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            getApplicationContext(), android.R.layout.simple_spinner_item, driverArray);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    driverSelectSpinner.setAdapter(spinnerArrayAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to update", Toast.LENGTH_SHORT).show();
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

    private void updateDriverForBooking() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Booking");
        String id = MainActivity.booking.getId();
        Booking b = MainActivity.booking;
        b.setDriverId(driverSelectSpinner.getSelectedItem().toString());
        collectionReference.document(id).set(b).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Booked Successfully with id " + id, Toast.LENGTH_SHORT).show();
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