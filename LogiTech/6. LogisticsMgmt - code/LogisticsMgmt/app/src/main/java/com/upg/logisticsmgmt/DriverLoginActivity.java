package com.upg.logisticsmgmt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upg.logisticsmgmt.pojo.Driver;
import com.upg.logisticsmgmt.pojo.User;

public class DriverLoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button driverLoginBtn;
    EditText driverName, driverPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        driverLoginBtn = findViewById(R.id.driverLoginBtn);
        driverLoginBtn.setOnClickListener(this);

        driverName = findViewById(R.id.driverLoginId);
        driverPassword = findViewById(R.id.driverPassword);
    }

    @Override
    public void onClick(View v) {
        if (v == driverLoginBtn) {
            if (validate()) {
                checkDriverLogin();

        } else {
            Toast.makeText(this, "Please check credentials", Toast.LENGTH_SHORT).show();
        }
        }
    }

    private boolean validate() {
        if(driverName.getText().toString().equals("") ) {
            driverName.setError("Please enter valid Name");
            return false;
        }
        if(driverPassword.getText().toString().equals("") ) {
            driverPassword.setError("Please enter valid Password");
            return false;
        }

        return true;
    }

    private  void checkDriverLogin() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("Drivers");
        collectionReference.document(driverName.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.getString("driverPassword") != null && document.getString("driverPassword").equals(driverPassword.getText().toString())) {
                        MainActivity.driver = document.toObject(Driver.class);
                        MainActivity.driver.setId(document.getId());
                        Intent intent = new Intent(getApplicationContext(),DriverDashboard.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //No record
                        Toast.makeText(getApplicationContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Failed
                    Toast.makeText(getApplicationContext(), "Something went wrong please try later", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Something went wrong please try later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}