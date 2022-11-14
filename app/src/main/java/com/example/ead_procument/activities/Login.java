package com.example.ead_procument.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_procument.R;
import com.example.ead_procument.model.User;


public class Login extends AppCompatActivity {


    private EditText email, password;
    private RadioButton manager, supplier;
    private Button login;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setMessage("Checking..");
        progressDialog.setCancelable(false);

        //login implemented with user mail and password
        login.setOnClickListener(v -> {

            if (email.getText().toString().equals("")) {
                Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                return;
            } else if (password.getText().toString().equals("")) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                return;
            }

            User.username = email.getText().toString();
            Intent intent = new Intent(Login.this,
                    ManagerLoggedActivity.class);
            startActivity(intent);
        });

    }

}