package com.abduljama.doctorwho;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    TextInputLayout   inputName, inputPhone ,inputEmail , inputPassword , inputPassword1;
    EditText editName  ,editEmail  , editPassword ,  editPassword1 , editPhone;
    Button btn_signup;
    ProgressDialog pDialog;

    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initializeViews();
        auth = FirebaseAuth.getInstance();
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpDialog();
                final String name = editName.getText().toString().trim();
                final String email = editEmail.getText().toString().trim();
                final String password = editPassword.getText().toString();
                String password1 = editPassword1.getText().toString();



                if ( TextUtils.isEmpty(name)){
                    hidepDialog();
                    Toast.makeText(getApplicationContext(), "Enter Name ", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    hidepDialog();
                    Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    hidepDialog();
                    Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.contentEquals(password1)){
                    Toast.makeText(getApplicationContext(), "Password Mismatch", Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    hidepDialog();
                    Toast.makeText(getApplicationContext(), "Enter phone number ", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                hidepDialog();
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, task.getException().getLocalizedMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    Log.d("TAG", task.getException().getLocalizedMessage());
                                }
                                else{
                                    createUser(name,email);
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();}
                            }
                        });
            }
        });
    }

    private void initializeViews() {
        // TextInputLayout
        inputName  = (TextInputLayout)findViewById(R.id.inputLayoutName);
        inputEmail = (TextInputLayout)findViewById(R.id.inputLayoutEmail);
        inputPassword = (TextInputLayout)findViewById(R.id.inputLayoutPassword);
        inputPassword1 = (TextInputLayout)findViewById(R.id.inputLayoutPassword1);

        // EditText
        editName = (EditText)findViewById(R.id.editName);
        editEmail= (EditText)findViewById(R.id.editEmail);
        //  editPhone = (EditText) findViewById(R.id.input_phoneNo);
        editPassword = (EditText)findViewById(R.id.editPassword);
        editPassword1 = (EditText)findViewById(R.id.editPassword1);
        //Buttons
        btn_signup =(Button)findViewById(R.id.btn_signup);
        // Progress Dialog
        pDialog = new ProgressDialog(SignUpActivity.this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }
    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void createUser(String email, String phone) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            User user1 = new User(email, phone);
            mFirebaseInstance = FirebaseDatabase.getInstance();
            mFirebaseDatabase = mFirebaseInstance.getReference("users");
            mFirebaseDatabase.child(user.getUid()).setValue(user1);
            SharedPreferences sharedpreferences = getSharedPreferences("MyPrefsUserID", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("UID", user.getUid());
            editor.apply();
        }
    }
}
