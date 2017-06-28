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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout inputEmail , inputPassword;
    EditText editEmail , editPassword;
    Button btn_login , btn_signup;
    ProgressDialog pDialog;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showpDialog();
                final String email = editEmail.getText().toString().trim();
                final String password = editPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter Email ", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter Password ", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password )
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                hidepDialog();
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {

                                    SharedPreferences sharedpreferences = getSharedPreferences("MyPrefEmail", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("email", email);
                                    editor.apply();
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } });
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent( getApplicationContext() , SignUpActivity.class);
                startActivity(in);
            }
        });
    }
    private void initialize() {
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPassword = (EditText)findViewById(R.id.editPassword);
        inputEmail = (TextInputLayout)findViewById(R.id.inputLayoutEmail);
        inputPassword = (TextInputLayout)findViewById(R.id.inputLayoutPassword);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_signup = (Button)findViewById(R.id.btn_signup);
        // Progress Dialog
        pDialog = new ProgressDialog(LoginActivity.this);
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
}
