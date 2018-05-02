package com.timbau3285.myinventory.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.timbau3285.myinventory.R;
import com.timbau3285.myinventory.database.SharedPreference;
import com.timbau3285.myinventory.database.DBHelper;

public class SignupActivity extends AppCompatActivity {

    private EditText edEmail, edPassword, edFullName, edConfirm;
    private Button register;
    private TextView loginLink;

    private String email, password, fullName, confirm;

    private SharedPreference userInfo;

    private Intent i;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edEmail = findViewById(R.id.edEmail);
        edFullName = findViewById(R.id.edFullname);
        edPassword = findViewById(R.id.edPassword);
        register = findViewById(R.id.btnRegister);
        edConfirm = findViewById(R.id.edConfirm);
        loginLink = findViewById(R.id.login_link);

        dbHelper = new DBHelper(this);
        userInfo = new SharedPreference(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (dbHelper.addSignupData(fullName, password, email)){

                        // setting info to sharedprefs
                        userInfo.setEmail(email);
                        userInfo.setFullName(fullName);
                        userInfo.setState(true);

                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Email has already been takne", Toast.LENGTH_LONG).show();
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Problem with form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private boolean validate(){
        boolean valid = true;

        email = edEmail.getText().toString().trim();
        password = edPassword.getText().toString().trim();
        fullName = edFullName.getText().toString().trim();
        confirm = edConfirm.getText().toString().trim();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            valid = false;
            edEmail.setError("Invalid Email");
        } else{
            edEmail.setError(null);
        }

        if (password.isEmpty() || confirm.isEmpty() || !password.equals(confirm)){
            valid = false;
            edPassword.setError("Invalid Password");
        } else{
            edPassword.setError(null);
        }

        if (fullName.isEmpty()){
            valid = false;
            edFullName.setError("Invalid Full Name");
        } else{
            edFullName.setError(null);
        }

        return valid;

    }

}
