package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.util.ArrayList;

public class AddVehicle extends AppCompatActivity implements View.OnClickListener {

    ImageView backToAdd;
    EditText truckNumber, permitId;
    Button addBtn;
    Spinner driverIdSpinner;
    ArrayList<String> driverList;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        driverList = new ArrayList();

        backToAdd = (ImageView)findViewById(R.id.backToAdd);
        backToAdd.setOnClickListener(this);

        truckNumber = findViewById(R.id.truckNumber);
        permitId = findViewById(R.id.permitId);

        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);

        driverIdSpinner = findViewById(R.id.driverIdSpinner);

        progressDialog = new ProgressDialog(AddVehicle.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        getAllDrivers();
    }

    @Override
    public void onClick(View v) {
        if(v == backToAdd) {
            Intent intent = new Intent(getApplicationContext(),VehicleManagment.class);
            startActivity(intent);
            finish();
        }

        if(v == addBtn) {

            if (validateVehicle()) {
                progressDialog = new ProgressDialog(AddVehicle.this);
                progressDialog.setCancelable(false); // set cancelable to false
                progressDialog.setMessage("Please Wait"); // set message
                progressDialog.show(); // show progress dialog
                createVehicle();
            } else {
                Toast.makeText(this, "Please check input fields", Toast.LENGTH_SHORT).show();
            }


        }
    }


    private boolean validateVehicle () {

        if(truckNumber.getText().toString().equals("")) {
            truckNumber.setError("Please enter valid Truck Number");
            return false;
        }
        if(permitId.getText().toString().equals("")) {
            permitId.setError("Please enter valid Permit Id");
            return false;
        }
        return true;
    }

private void createVehicle() {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = firebaseFirestore.collection("Vehicles");
    Vehicle v = new Vehicle();
    v.setDriverId(driverIdSpinner.getSelectedItem().toString());
    v.setTruckNumber(truckNumber.getText().toString());
    v.setPermitId(permitId.getText().toString());
    collectionReference.document().set(v).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Vehicle Created Successfully", Toast.LENGTH_SHORT).show();
            }
            truckNumber.setText("");
            permitId.setText("");
            progressDialog.dismiss();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(getApplicationContext(), "Failed to create", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    });
}
    private void getAllDrivers() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Drivers");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        driverList.add(document.getId());
                    }

                    String[] driverArray = new String[driverList.size()];
                    driverArray = driverList.toArray(driverArray);
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            getApplicationContext(), android.R.layout.simple_spinner_item, driverArray);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    driverIdSpinner.setAdapter(spinnerArrayAdapter);
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