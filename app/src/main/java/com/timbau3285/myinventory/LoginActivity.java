package com.timbau3285.myinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText edEmail, edPassword;
    private Button login;
    private TextView signup;

    private String email, password;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassword);
        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.txtRegister);

        email = edEmail.getText().toString().trim();
        password = edPassword.getText().toString().trim();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });


    }
}