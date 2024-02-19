package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
import com.upg.logisticsmgmt.adapter.VehicleAdapter;
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.util.ArrayList;

public class VehicleManagment extends AppCompatActivity implements View.OnClickListener {

    ImageView backArrow, addVehicle;
    ListView vehicleList;
    ArrayList<Vehicle> arrayList = new ArrayList<>();
    VehicleAdapter adapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_managment);

        backArrow = findViewById(R.id.VehicleBackArrow);
        backArrow.setOnClickListener(this);

        addVehicle = findViewById(R.id.addVehi);
        addVehicle.setOnClickListener(this);

        vehicleList = findViewById(R.id.vehicleList);


        progressDialog = new ProgressDialog(VehicleManagment.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        getAllVehicles();
//        arrayList.add(new Vehicle("KA1234", " 33344","1000034"));
//        arrayList.add(new Vehicle("TN5433", " 44455","1000036"));
//        arrayList.add(new Vehicle("TL2353", " 66677","1000038"));


    }

    @Override
    public void onClick(View v) {
        if (v == backArrow) {
            Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
            startActivity(intent);
            finish();
        }

        if(v == addVehicle) {
            Intent intent = new Intent(getApplicationContext(),AddVehicle.class);
            startActivity(intent);
            finish();
        }
    }

    private void getAllVehicles() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Vehicles");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Vehicle v = document.toObject(Vehicle.class);
                        arrayList.add(v);
                    }

                    adapter = new VehicleAdapter(getApplicationContext(), arrayList);
                    vehicleList.setAdapter(adapter);
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