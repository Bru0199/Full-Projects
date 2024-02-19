package com.upg.logisticsmgmt.ui.main;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upg.logisticsmgmt.R;
import com.upg.logisticsmgmt.pojo.User;

import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class CustomerSignUpFragment extends Fragment implements View.OnClickListener {

    EditText userName, contactNum, registerEmailId, registerPassword;
    Button userLogin;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.customer_signup, container, false);

        userName = root.findViewById(R.id.userName);
        contactNum = root.findViewById(R.id.contactNum);
        registerEmailId = root.findViewById(R.id.registerEmailId);
        registerPassword = root.findViewById(R.id.registerPassword);
        userLogin = root.findViewById(R.id.userLogin);
        userLogin.setOnClickListener(this);

        return root;
    }

    private void createUser() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

        User user = new User();
        user.setName(userName.getText().toString());
        user.setEmail(registerEmailId.getText().toString());
        user.setContactNum(contactNum.getText().toString());
        user.setPassword(registerPassword.getText().toString());

        CollectionReference collectionReference = firebaseFirestore.collection("User");
        collectionReference.document(registerEmailId.getText().toString()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Document created/updated", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed to update", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == userLogin) {

            if (validateUser()) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setCancelable(false); // set cancelable to false
                progressDialog.setMessage("Please Wait"); // set message
                progressDialog.show(); // show progress dialog

                createUser();
            } else {

                Toast.makeText(getContext(), "Please check input fields", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private boolean validateUser () {

        if(userName.getText().toString().equals("") ) {
            userName.setError("Please enter valid user Name");
            return false;
        }
        if(contactNum.getText().toString().equals("") ) {
            contactNum.setError("Please enter valid Phone Number");
            return false;
        }

        if(registerEmailId.getText().toString().equals("") ) {
            registerEmailId.setError("Please enter valid emailID");
            return false;
        }
        if(registerPassword.getText().toString().equals("") ) {
            registerPassword.setError("Please enter valid Password");
            return false;
        }


        return true;
    }
}