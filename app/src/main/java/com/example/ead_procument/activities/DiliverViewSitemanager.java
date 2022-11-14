package com.example.ead_procument.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_procument.R;
import com.example.ead_procument.adapter.view.OrderAdapter;
import com.example.ead_procument.model.Order;
import com.example.ead_procument.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiliverViewSitemanager extends AppCompatActivity {

    DatabaseReference reference;
    private ArrayAdapter<CharSequence> adapter;
    private ProgressDialog dialog;
    private String statusTxt = "All orders";
    private ArrayList<Order> orderList;
    private RecyclerView recyclerView;
    private Context context;
    private FloatingActionButton btnAddAction;
    private DiliverViewSitemanager orderViewSitemanager;
    private OrderAdapter orderAdapter;

    public static Order editing;


    //viewing the deliveries function implemented
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        orderViewSitemanager = this;
        super.onCreate(savedInstanceState);
        orderList = new ArrayList<>();
        setContentView(R.layout.activity_diliver_view_sitemanager);
        recyclerView = findViewById(R.id.recyclerView);

        getOrders();

    }


    public void delete(Order order) {

        AlertDialog.Builder builder = new AlertDialog.Builder(DiliverViewSitemanager.this);
        builder.setTitle("Are you sure you want to delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseReference delete_Order = FirebaseDatabase.getInstance().getReference("orders").child(order.getOrderId());
                delete_Order.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(DiliverViewSitemanager.this, "deleted ", Toast.LENGTH_SHORT).show();


                        } else {
                            Toast.makeText(DiliverViewSitemanager.this, "Not deleted ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                ;
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();

    }

    public void update(Order order) {
        Log.d("order q", order.getOrderId());
        editing = order;
        Log.d("order q1", editing.getOrderId());
        Intent intent = new Intent(DiliverViewSitemanager.this, DeliveryUpdateSitemanager.class);
        startActivity(intent);
    }

    public void getOrders() {

        reference = FirebaseDatabase.getInstance().getReference("orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                orderList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    Order order = postSnapshot.getValue(Order.class);
                    if (order.getUsername().equals(User.username)) {
                        orderList.add(order);
                    }
                    Log.d("orderq", orderList.toString());
                    // here you can access to name property
                }


                Log.d("order", "out");
                orderAdapter = new OrderAdapter(orderViewSitemanager, DiliverViewSitemanager.this, orderList);
                Log.d("order", orderList.toString());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DiliverViewSitemanager.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(orderAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

    }

}