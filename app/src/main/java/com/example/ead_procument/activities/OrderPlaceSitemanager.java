package com.example.ead_procument.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
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


public class OrderPlaceSitemanager extends AppCompatActivity {


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
    private OrderPlaceSitemanager thisRef;

    private EditText companyName;
    private EditText phone;
    private EditText quantity;
    private EditText dateRequired;
    private EditText siteAddress;
    private EditText price;
    private EditText notes;
    private TextView total;


    private Button btn_sendForApproval, btn_placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisRef = this;
        productList = new ArrayList<>();
        supplierList = new ArrayList<>();
        productNameList = new ArrayList<>();

        setContentView(R.layout.activity_order_place_sitemanager);


        //making the two drop down lists for product and suppliers
        product = (Spinner) findViewById(R.id.spinner_product);
        supplier = findViewById(R.id.spinner_supplier);
        companyName = findViewById(R.id.editText_companyName);
        phone = findViewById(R.id.editTextPhone);
        quantity = findViewById(R.id.editText_quantity);
        quantity.setEnabled(false);
        dateRequired = findViewById(R.id.editText_dateRequired);
        siteAddress = findViewById(R.id.editTextTextPostalAddress);
        total = findViewById(R.id.total);
        btn_placeOrder = findViewById(R.id.btn_placeOrder);

        //product spinner implementation
        getProdcuts();
        productSpinnerAdapter = new ProductSpinnerAdapter(this, OrderPlaceSitemanager.this, productList);
        product.setAdapter(productSpinnerAdapter);



        //supplier spinner implementation
        getSupplier();
        supplierSpinnerAdapter = new SupplierSpinnerAdapter(this, OrderPlaceSitemanager.this, supplierList);
        supplier.setAdapter(supplierSpinnerAdapter);

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int qty = 0;
                try {
                    qty = Integer.parseInt(quantity.getText().toString());
                } catch (Exception e) {
                    Log.e("error", e.toString());
                }
                float totalPrice = Calculation.totalAmountCal(qty,selectedProduct.getUnitprice());
                total.setText(String.valueOf(totalPrice));
            }
        });

        btn_placeOrder.setOnClickListener(v -> {
            save();
        });

    }

    //saving the data inserted in the order create form by site manager
    private void save() {
        Order order = new Order();
        order.setOrderId(String.valueOf(System.currentTimeMillis()));
        order.setUsername(User.username);
        order.setNumberofunitorder(quantity.getText().toString());
        order.setNumoforderdeliver(String.valueOf(0));
        order.setNumoforderretrive(order.getNumberofunitorder());
        order.setCompanyName(companyName.getText().toString());
        order.setPhone(phone.getText().toString());
        order.setDate(dateRequired.getText().toString());
        order.setProductname(selectedProduct.getProductname());
        order.setSuppliername(selectedSupplier.getSuppliername());
        order.setAddress(siteAddress.getText().toString());
        order.setTotalbill(total.getText().toString());
        insert(order);
    }

    //firebase implementation for the place order function
    private void insert(Order order) {
        reference = FirebaseDatabase.getInstance().getReference("orders");
        reference.child(order.getOrderId()).setValue(order, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {

                    Toast.makeText(getBaseContext(), databaseError.toString(), Toast.LENGTH_SHORT);
                } else {
                    Intent intent = new Intent(OrderPlaceSitemanager.this, OrderViewSitemanager.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    //getting the available products
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
                    productNameList.add(product.getProductname());
                    // here you can access to name property
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });
    }

    //getting the available suppliers
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

    //select one product
    public void selectProduct(int position) {
        Log.d("spinner", String.valueOf(position));
        selectedProduct = productList.get(position);
        quantity.setEnabled(true);
        product.setSelection(position);
        Log.d("selected", product.getSelectedItem().toString());
    }

    //select one supplier
    public void selectSupplier(int position) {
        Log.d("spinner", String.valueOf(position));
        selectedSupplier = supplierList.get(position);
        supplier.setSelection(position);
        Log.d("selected", supplier.getSelectedItem().toString());
    }

}