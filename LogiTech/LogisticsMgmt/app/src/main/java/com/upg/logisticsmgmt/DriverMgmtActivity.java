package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.upg.logisticsmgmt.adapter.DriverListAdapter;
import com.upg.logisticsmgmt.adapter.VehicleAdapter;
import com.upg.logisticsmgmt.pojo.Driver;
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.util.ArrayList;

public class DriverMgmtActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backArrow, addDriver;
    ListView driverList;
    ArrayList<Driver> arrayList = new ArrayList<>();
    DriverListAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_mgmt);

        backArrow = findViewById(R.id.driverMgmtBackBtn);
        backArrow.setOnClickListener(this);


        addDriver = findViewById(R.id.createDriver);
        addDriver.setOnClickListener(this);

        driverList = findViewById(R.id.driverList);
        progressDialog = new ProgressDialog(DriverMgmtActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        getAllDriverList();

    }

    @Override
    public void onClick(View v) {
        if (v == backArrow) {
            Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
            startActivity(intent);
            finish();
        }

        if (v == addDriver) {
            Intent intent = new Intent(getApplicationContext(),CreateDriverActivity.class);
            startActivity(intent);
//            finish();
        }
    }

    private void getAllDriverList() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Drivers");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Driver d = document.toObject(Driver.class);
                        arrayList.add(d);
                    }

                    adapter = new DriverListAdapter(getApplicationContext(), arrayList);
                    driverList.setAdapter(adapter);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to load", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}