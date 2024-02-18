package com.upg.logisticsmgmt.ui.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.upg.logisticsmgmt.AdminDashboard;
import com.upg.logisticsmgmt.MainActivity;
import com.upg.logisticsmgmt.R;
import com.upg.logisticsmgmt.UserDashboard;
import com.upg.logisticsmgmt.pojo.User;

/**
 * A placeholder fragment containing a simple view.
 */
public class CustomerSignInFragment extends Fragment implements View.OnClickListener {

    Button signInBtn;
    EditText userEmailId, userPassword;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.customer_signin, container, false);
        signInBtn = root.findViewById(R.id.userLogin);
        signInBtn.setOnClickListener(this);

        userEmailId = root.findViewById(R.id.userEmailId);
        userPassword = root.findViewById(R.id.userPassword);

        return root;
    }

    @Override
    public void onClick(View v) {
        if(v == signInBtn) {
            if(validateLogin()) {
                progressDialog = new ProgressDialog(getContext());
                progressDialog.setCancelable(false); // set cancelable to false
                progressDialog.setMessage("Please Wait"); // set message
                progressDialog.show(); // show progress dialog
                checkLogin();
            } else {
                Toast.makeText(getContext(), "Please check credentials", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private boolean validateLogin() {

        if(userEmailId.getText().toString().equals("") ) {
            userEmailId.setError("Please enter valid emailID");
            return false;
        }
        if(userPassword.getText().toString().equals("") ) {
            userPassword.setError("Please enter valid Password");
            return false;
        }

        return true;
    }

    private void checkLogin() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = firebaseFirestore.collection("User");
        collectionReference.document(userEmailId.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.getString("password") != null && document.getString("password").equals(userPassword.getText().toString())) {
                        MainActivity.user = document.toObject(User.class);
                        Intent intent = new Intent(getActivity().getApplicationContext(), UserDashboard.class);
                        startActivity(intent);
                        getActivity().finish();
                    } else {
                        //No record
                        Toast.makeText(getContext(), "No Record Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Failed
                    Toast.makeText(getContext(), "Something went wrong please try later", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong please try later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}