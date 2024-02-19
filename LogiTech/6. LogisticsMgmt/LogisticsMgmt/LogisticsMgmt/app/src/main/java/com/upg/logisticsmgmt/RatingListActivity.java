package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
import com.upg.logisticsmgmt.adapter.RatingListAdapter;
import com.upg.logisticsmgmt.pojo.Driver;
import com.upg.logisticsmgmt.pojo.Rating;

import java.util.ArrayList;

public class RatingListActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backToAdmin;
    ListView ratingList;

    ArrayList<Rating> arrayList = new ArrayList<>();
    RatingListAdapter adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_list);

        backToAdmin = findViewById(R.id.backToAdmin);
        backToAdmin.setOnClickListener(this);

        ratingList = findViewById(R.id.ratingList);

        progressDialog = new ProgressDialog(RatingListActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        getRatingList();
    }

    @Override
    public void onClick(View v) {
        if (v == backToAdmin) {
            Intent intent = new Intent(getApplicationContext(),AdminDashboard.class);
            startActivity(intent);
            finish();
        }
    }

    private void getRatingList() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Rating");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Rating d = document.toObject(Rating.class);
                        arrayList.add(d);
                    }

                    adapter = new RatingListAdapter(getApplicationContext(), arrayList);
                    ratingList.setAdapter(adapter);
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