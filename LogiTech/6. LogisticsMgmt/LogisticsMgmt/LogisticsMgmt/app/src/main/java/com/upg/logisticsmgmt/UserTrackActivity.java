package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.upg.logisticsmgmt.adapter.BookingAdapter;
import com.upg.logisticsmgmt.adapter.UserTrackingAdapter;
import com.upg.logisticsmgmt.pojo.Booking;
import com.upg.logisticsmgmt.pojo.BookingWithLocation;
import com.upg.logisticsmgmt.pojo.Driver;

import java.util.ArrayList;

public class UserTrackActivity extends AppCompatActivity {

    ListView latestUpdate;
    ArrayList<BookingWithLocation> arrayList = new ArrayList<>();
    UserTrackingAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_track);

        latestUpdate = findViewById(R.id.latestUpdate);

        progressDialog = new ProgressDialog(UserTrackActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        adapter = new UserTrackingAdapter(UserTrackActivity.this, arrayList);
        latestUpdate.setAdapter(adapter);
        getMyBooking();
    }

    private void getMyBooking() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Booking");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

//                       getting driver latest location starts here

                        Booking booking = document.toObject(Booking.class);
                        BookingWithLocation b = new BookingWithLocation();
                        b.setCreatedBy(booking.getCreatedBy());
                        b.setFrom(booking.getFrom());
                        b.setCreatedOn(booking.getCreatedOn());
                        b.setDriverId(booking.getDriverId());
                        b.setTo(booking.getTo());
                        b.setWeight(booking.getWeight());
                        b.setWeightUnit(booking.getWeightUnit());

                        if (booking.getCreatedBy().equals(MainActivity.user.getEmail())) {
                            if (booking.getDriverId() != null) {
                                CollectionReference cr2 = firebaseFirestore.collection("Drivers");
                                cr2.document(booking.getDriverId()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document != null ) {
                                                if (document.get("currentLocation") != null) {
                                                    b.setCurrentLocation(document.get("currentLocation").toString());
                                                } else {
                                                    b.setCurrentLocation("");
                                                }
                                                arrayList.add(b);
                                                adapter.notifyDataSetChanged();
                                            } else {
                                                //No record
                                                Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            // Failed
                                            Toast.makeText(getApplicationContext(), "Something went wrong please try later", Toast.LENGTH_SHORT).show();
                                        }
//                                        adapter = new BookingAdapter(UserTrackActivity.this, arrayList);
                                        adapter.notifyDataSetChanged();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "Something went wrong please try later", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                b.setCurrentLocation("No Driver Assigned yet");
                                arrayList.add(b);
                                adapter.notifyDataSetChanged();
                            }
                        }
//                        ends here

                    }


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
}