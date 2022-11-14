package com.example.ead_procument.adapter.spinners;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ead_procument.R;
import com.example.ead_procument.activities.OrderPlaceSitemanager;
import com.example.ead_procument.activities.OrderUpdateSitemanager;
import com.example.ead_procument.model.Product;

import java.util.ArrayList;

public class ProductSpinnerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> list;
    private int flag;
    private OrderPlaceSitemanager placeSitemanager;
    private OrderUpdateSitemanager orderUpdateSitemanager;

    //product spinner
    public ProductSpinnerAdapter(OrderPlaceSitemanager orderPlaceSitemanager, Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
        this.flag = 0;
        this.placeSitemanager = orderPlaceSitemanager;
    }

    public ProductSpinnerAdapter(OrderUpdateSitemanager orderPlaceSitemanager, Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
        this.flag = 1;
        this.orderUpdateSitemanager = orderPlaceSitemanager;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.layout_spinner_order, viewGroup, false);
        TextView text1 = view1.findViewById(R.id.text1);
        text1.setText(list.get(i).getProductname());

        text1.setOnClickListener(v -> {
            if (flag == 0) {
                placeSitemanager.selectProduct(i);
            } else {
                orderUpdateSitemanager.selectProduct(i);
            }
            notifyDataSetChanged();
        });


        return view1;
    }
}
