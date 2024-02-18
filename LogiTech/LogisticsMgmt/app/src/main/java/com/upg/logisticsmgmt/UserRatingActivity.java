package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upg.logisticsmgmt.pojo.Rating;

import java.util.Date;

public class UserRatingActivity extends AppCompatActivity implements View.OnClickListener {

    RatingBar ratingbar;
    Button submitBtn;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_rating);

        submitBtn = findViewById(R.id.submitRating);
        submitBtn.setOnClickListener(this);

        ratingbar = findViewById(R.id.ratingBar);
    }

    @Override
    public void onClick(View v) {
        if (v == submitBtn) {
            progressDialog = new ProgressDialog(UserRatingActivity.this);
            progressDialog.setCancelable(false); // set cancelable to false
            progressDialog.setMessage("Please Wait"); // set message
            progressDialog.show(); // show progress dialog
            submitRating();
        }
    }

    private void submitRating() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Rating");
        Rating r = new Rating();

        r.setCreatedBy(MainActivity.user.getEmail());
        r.setCreatedOn(new Date().toString());
        r.setRatingValue(String.valueOf(ratingbar.getRating()));
        String id = collectionReference.document().getId();
        collectionReference.document(id).set(r).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Rating submitted Successfully with id " + id, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                    startActivity(intent);
                    finish();
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