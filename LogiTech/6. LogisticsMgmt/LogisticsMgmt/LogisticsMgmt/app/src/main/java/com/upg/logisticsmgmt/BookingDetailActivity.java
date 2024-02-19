package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upg.logisticsmgmt.pojo.Booking;

import java.util.Date;

public class BookingDetailActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressDialog progressDialog;
    Button bookBtn;
    Spinner fromSpinner, toSpinner, weightUnit;
    EditText weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_detail);

        bookBtn = findViewById(R.id.bookBtn);
        bookBtn.setOnClickListener(this);

        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        weightUnit = findViewById(R.id.unitSpinner);

        weight = findViewById(R.id.weight);

    }

    private void createBooking(Booking b) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Booking");
        String id = collectionReference.document().getId();
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

    @Override
    public void onClick(View v) {
        if (v == bookBtn) {
            progressDialog = new ProgressDialog(BookingDetailActivity.this);
            progressDialog.setCancelable(false); // set cancelable to false
            progressDialog.setMessage("Please Wait"); // set message
            progressDialog.show(); // show progress dialog
            Booking b = new Booking();
            b.setCreatedBy(MainActivity.user.getEmail());
            b.setCreatedOn(new Date().toString());
            b.setFrom(fromSpinner.getSelectedItem().toString());
            b.setTo(toSpinner.getSelectedItem().toString());
            b.setWeight(weight.getText().toString());
            b.setWeightUnit(weightUnit.getSelectedItem().toString());
            createBooking(b);
        }
    }
}