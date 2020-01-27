package com.example.android_foodbot.AccountActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_foodbot.Common.Common;
import com.example.android_foodbot.HomeActivity;
import com.example.android_foodbot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText inputName,  inputPhone, inputEmail,  inputPassword;
    private FirebaseAuth auth;
    private Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();

        btnLogin = (Button) findViewById(R.id.btn_for_login);
        btnSignUp = (Button) findViewById(R.id.btn_for_signUp);
        inputName = (EditText) findViewById(R.id.name);
        inputPhone = (EditText) findViewById(R.id.phone);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Common.isConnectedToInternet(getBaseContext())) {
                    final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                    mDialog.setMessage("Loading... Please wait!");
                    mDialog.show();

                    final String name = inputName.getText().toString().trim();
                    final String phone = inputPhone.getText().toString().trim();
                    final String email = inputEmail.getText().toString().trim();
                    String password = inputPassword.getText().toString().trim();

                    if (TextUtils.isEmpty(name)) {
                        mDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "Enter your name!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(phone)) {
                        mDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "Enter your phone number!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(email)) {
                        mDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(password)) {
                        mDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (password.length() < 6) {
                        mDialog.dismiss();

                        Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        final User user = new User(
                                                name,
                                                email,
                                                phone
                                        );

                                        FirebaseDatabase.getInstance().getReference("Users")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                mDialog.dismiss();
                                                if (task.isSuccessful()) {
                                                    mDialog.dismiss();
                                                    Common.currentUser = user;

                                                    Intent intentLogin = new Intent(SignUpActivity.this, HomeActivity.class);
                                                    startActivity(intentLogin);
                                                    finish();
//
                                                } else {

                                                }
                                            }
                                        });

                                    } else {
                                        mDialog.dismiss();
                                        Toast.makeText(SignUpActivity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Please check your internet connection !!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        });

    }


}
