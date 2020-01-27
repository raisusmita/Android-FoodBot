package com.example.android_foodbot.AccountActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_foodbot.Common.Common;
import com.example.android_foodbot.HomeActivity;
import com.example.android_foodbot.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private Button btnSignUp, btnLogin;

    CheckBox ckbRemember;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignUp = (Button) findViewById(R.id.btn_signUp);
//        ckbRemember = (CheckBox)findViewById(R.id.ckbRemember);

        //Init Paper
        Paper.init(this);

        auth = FirebaseAuth.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Common.isConnectedToInternet(getBaseContext())) {

                    final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                    mDialog.setMessage("Loading... Please wait!");
                    mDialog.show();

                    String email = inputEmail.getText().toString();
                    final String password = inputPassword.getText().toString();

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


                    auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {

                                        if (password.length() < 6) {
                                            inputPassword.setError(getString(R.string.minimum_password));
                                        } else {
                                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        mDialog.dismiss();

                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();


                                        FirebaseDatabase.getInstance().getReference().child("Users").child(uid).addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                                User user = dataSnapshot.child("name").getValue(User.class);
                                                User user = dataSnapshot.getValue(User.class);
//
//                                                if (user==null){
//                                                    Toast.makeText(LoginActivity.this, "NO user found", Toast.LENGTH_SHORT).show();
//                                                }
//                                                else{
//                                                    Toast.makeText(LoginActivity.this, "user is "+user.getName(), Toast.LENGTH_SHORT).show();
//                                                }
                                                Common.currentUser = user;
                                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                                Toast.makeText(LoginActivity.this, "Common user name is " + Common.currentUser.getName(), Toast.LENGTH_SHORT).show();
                                                startActivity(intent);
                                                finish();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                throw databaseError.toException();

                                            }
                                        });
//                                                                                    Toast.makeText(LoginActivity.this, "Common user name is " + Common.currentUser, Toast.LENGTH_SHORT).show();


                                    }

                                }
                            });

                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please check your internet connection !!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

}
