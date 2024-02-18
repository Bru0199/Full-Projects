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
import com.upg.logisticsmgmt.adapter.BookingAdapter;
import com.upg.logisticsmgmt.adapter.OrderAdapter;
import com.upg.logisticsmgmt.pojo.Booking;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView orderBackBtn;
    ListView orderList;
    ArrayList<Booking> arrayList = new ArrayList<>();
    OrderAdapter adapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        orderBackBtn = findViewById(R.id.orderBackBtn);
        orderBackBtn.setOnClickListener(this);

        orderList = findViewById(R.id.orderList);
        progressDialog = new ProgressDialog(OrderListActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog
        getMyOrders();
    }

    @Override
    public void onClick(View v) {
        if (v == orderBackBtn) {
            Intent intent = new Intent(getApplicationContext(),DriverDashboard.class);
            startActivity(intent);
            finish();
        }
    }

    private void getMyOrders() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Booking");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Booking b = document.toObject(Booking.class);
                        b.setId(document.getId());
                        if(MainActivity.driver.getDriverId().equals(b.getDriverId())){
                            arrayList.add(b);
                        }

                    }
                    adapter = new OrderAdapter(OrderListActivity.this, arrayList);
                    orderList.setAdapter(adapter);

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