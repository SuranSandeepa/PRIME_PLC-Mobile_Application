package com.example.ead_procument.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ead_procument.R;
import com.example.ead_procument.adapter.spinners.ProductSpinnerAdapter;
import com.example.ead_procument.adapter.spinners.SupplierSpinnerAdapter;
import com.example.ead_procument.model.Order;
import com.example.ead_procument.model.Product;
import com.example.ead_procument.model.Supplier;
import com.example.ead_procument.model.User;
import com.example.ead_procument.services.Calculation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeliveryUpdateSitemanager extends AppCompatActivity {


    private Spinner product;
    private Spinner supplier;
    private ArrayList<Product> productList;
    private List<String> productNameList;
    private Product selectedProduct = null;
    private Supplier selectedSupplier = null;
    private ArrayList<Supplier> supplierList;
    private List<String> supplierNameList;
    private ProductSpinnerAdapter productSpinnerAdapter;
    private SupplierSpinnerAdapter supplierSpinnerAdapter;
    private DatabaseReference reference;
    private Order prop;


    private EditText qty;
    private EditText phone;
    private EditText quantity;
    private EditText dateRequired;
    private EditText siteAddress;
    private EditText price;
    private EditText notes;
    private TextView total;
    private TextView id;


    private Button btn_sendForApproval, btn_placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prop = DiliverViewSitemanager.editing;
        setContentView(R.layout.activity_diliver_update_sitemanager);

        qty = findViewById(R.id.rcQty);
        btn_placeOrder = findViewById(R.id.update);
        btn_placeOrder.setOnClickListener(v -> {
            save();
        });

    }


    //saving the updated quantity data on the deliveries function
    private void save() {

        Log.d("order 2", prop.getOrderId());
        int recQty = Integer.parseInt(qty.getText().toString());
        int delQty = Calculation.deliveredQrt(prop.getNumoforderdeliver(),recQty);
        int ret = Calculation.retrievedQty(prop.getNumberofunitorder(),delQty);
        Order order = new Order();
        order.setOrderId(prop.getOrderId());
        order.setUsername(User.username);
        order.setNumberofunitorder(prop.getNumberofunitorder());
        order.setNumoforderdeliver(String.valueOf(delQty));
        order.setNumoforderretrive(String.valueOf(ret));
        order.setCompanyName(prop.getCompanyName());
        order.setPhone(prop.getPhone());
        order.setDate(prop.getDate());
        order.setProductname(prop.getProductname());
        order.setSuppliername(prop.getSuppliername());
        order.setTotalbill(prop.getTotalbill());
        insert(order);
    }

    //firebase data insertion for the deliveries update function
    private void insert(Order order) {
        reference = FirebaseDatabase.getInstance().getReference("orders");
        reference.child(prop.getOrderId()).setValue(order, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {

                    Toast.makeText(getBaseContext(), databaseError.toString(), Toast.LENGTH_SHORT);
                } else {
                    Intent intent = new Intent(DeliveryUpdateSitemanager.this, DiliverViewSitemanager.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }

    private void getProdcuts() {
        reference = FirebaseDatabase.getInstance().getReference("products");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Log.d("pro", snapshot.toString());
                    Product product = postSnapshot.getValue(Product.class);
                    Log.d("pro", product.toString());
                    productList.add(product);
                    // here you can access to name property like university.name
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }

    private void getSupplier() {
        reference = FirebaseDatabase.getInstance().getReference("suppliers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                supplierList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Log.d("pro", snapshot.toString());
                    Supplier supplier = postSnapshot.getValue(Supplier.class);
                    Log.d("pro", supplier.toString());
                    supplierList.add(supplier);
                    // here you can access to name property
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }

    private void test() {
        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("aaaaaa", "11111111");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void selectProduct(int position) {
        Log.d("spinner", String.valueOf(position));
        selectedProduct = productList.get(position);
        quantity.setEnabled(true);
        product.setSelection(position);
        Log.d("selected", product.getSelectedItem().toString());
    }

    public void selectSupplier(int position) {
        Log.d("spinner", String.valueOf(position));
        selectedSupplier = supplierList.get(position);
        supplier.setSelection(position);
        Log.d("selected", supplier.getSelectedItem().toString());
    }

}