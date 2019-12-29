package com.yogi.financeapp;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yogi.financeapp.Fragments.InsuranceDetailFragment;
import com.yogi.financeapp.models.GetQuoteModel;

import java.net.Inet4Address;

public class GetQuoteDialog extends AppCompatDialogFragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("QuoteUsers");

    private String insuranceName;
    private Context context;
    public GetQuoteDialog(String insuranceName, Context context) {
        this.insuranceName = insuranceName;
        this.context = context;
    }


    private static final String TAG = "GetQuoteDialog";

    private TextInputLayout nameTextInputLayout, emailTextInputLayout, phoneTextInputLayout;
    private TextView insuranceNameTextView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable final Bundle savedInstanceState) {
        AlertDialog.Builder builder =
                 new AlertDialog.Builder(getContext());


        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.get_quote_dialog, null);

        firebaseAuth = FirebaseAuth.getInstance();

        nameTextInputLayout = view.findViewById(R.id.name_get_quote);
        emailTextInputLayout = view.findViewById(R.id.email_get_quote);
        phoneTextInputLayout = view.findViewById(R.id.phone_get_quote);
        insuranceNameTextView = view.findViewById(R.id.insurance_name_quote_text_view);


        insuranceNameTextView.setText(insuranceName);


        Log.d(TAG, "onCreateDialog: desc: " + insuranceName);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };

        builder.setView(view)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameTextInputLayout.getEditText().getText().toString().trim();
                        String phone = phoneTextInputLayout.getEditText().getText().toString().trim();
                        String email = emailTextInputLayout.getEditText().getText().toString().trim();
                        Log.d(TAG, "onClick: name: " + name);
                        saveQuoteRequest(name, email, phone);
                    }
                });



        return builder.create();

    }

    private void saveQuoteRequest(String name, String email, String phone) {

        if (validateEmail() & validatePhone() & validateUsername()) {
            GetQuoteModel getQuoteModel = new GetQuoteModel(insuranceName, name, email, phone);
            Log.d(TAG, "saveQuoteRequest: model: " + getQuoteModel.getInsuranceName());
            Log.d(TAG, "saveQuoteRequest: model: " + getQuoteModel.getName());
            Log.d(TAG, "saveQuoteRequest: model: " + getQuoteModel.getEmail());
            Log.d(TAG, "saveQuoteRequest: model: " + getQuoteModel.getPhone());

            collectionReference.add(getQuoteModel)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "onSuccess: ");
                            Toast.makeText(context, "Success, Request Sent!", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: Error: " + e.getMessage());
                        }
                    });
        }
    }

    private boolean validatePhone() {
        String phone = phoneTextInputLayout.getEditText().getText().toString().trim();

        if (phone.isEmpty()) {
            phoneTextInputLayout.setError("Field can't be empty");
            return false;
        }
        return true;
    }

    private boolean validateUsername() {

        String usernameInput = nameTextInputLayout.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            nameTextInputLayout.setError("Field can't be empty");
            return false;
        } else {
            nameTextInputLayout.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {

        String emailInput = emailTextInputLayout.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            emailTextInputLayout.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailTextInputLayout.setError("Please enter a valid email address");
            Toast.makeText(context, "Enter a valid Email", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            emailTextInputLayout.setError(null);
            return true;
        }
    }
}
