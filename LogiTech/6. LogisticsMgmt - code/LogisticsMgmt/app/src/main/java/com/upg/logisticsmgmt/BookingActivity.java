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
import com.upg.logisticsmgmt.adapter.BookingAdapter;
import com.upg.logisticsmgmt.adapter.VehicleAdapter;
import com.upg.logisticsmgmt.pojo.Booking;
import com.upg.logisticsmgmt.pojo.Vehicle;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity implements View.OnClickListener {

    ListView bookingList;
    ArrayList<Booking> arrayList = new ArrayList<>();
    BookingAdapter adapter;
    ImageView backBtn;
//    ArrayList<String> bookingList;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        bookingList = findViewById(R.id.bookingList);

        backBtn = findViewById(R.id.bookingBackBtn);
        backBtn.setOnClickListener(this);
        progressDialog = new ProgressDialog(BookingActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        getAllBooking();

    }

    @Override
    public void onClick(View v) {
        if (v == backBtn) {
            Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
            startActivity(intent);
            finish();
        }

    }

    private void getAllBooking () {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Booking");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Booking b = document.toObject(Booking.class);
                        b.setId(document.getId());
                        arrayList.add(b);
                    }
                    adapter = new BookingAdapter(BookingActivity.this, arrayList);
                    bookingList.setAdapter(adapter);

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