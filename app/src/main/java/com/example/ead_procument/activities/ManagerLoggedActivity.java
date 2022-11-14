package com.example.ead_procument.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_procument.R;


public class ManagerLoggedActivity extends AppCompatActivity {

    Button orderManage, deliverManage;
    ImageView logout;
    Button payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_logged);


        orderManage = findViewById(R.id.btn_order);
        logout = findViewById(R.id.logout);
        deliverManage = findViewById(R.id.receipt_manage);

        orderManage.setOnClickListener(v -> {
            Intent intent = new Intent(ManagerLoggedActivity.this, OrderViewSitemanager.class);
            startActivity(intent);

        });

        deliverManage.setOnClickListener(v -> {
            Intent intent = new Intent(ManagerLoggedActivity.this, DiliverViewSitemanager.class);
            startActivity(intent);

        });

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(ManagerLoggedActivity.this, Login.class);
            startActivity(intent);

        });
    }
}