package com.example.ead_procument.adapter.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ead_procument.R;
import com.example.ead_procument.activities.DiliverViewSitemanager;
import com.example.ead_procument.activities.OrderViewSitemanager;
import com.example.ead_procument.listiner.OnOrderClicked;
import com.example.ead_procument.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Order> list;
    private OnOrderClicked orderClicked;
    int flag;
    private OrderViewSitemanager orderViewSitemanager;
    private DiliverViewSitemanager diliverViewSitemanager;


    public OrderAdapter(OrderViewSitemanager orderViewSitemanager, Context context, ArrayList<Order> list) {
        Log.d("order", "in in");
        this.context = context;
        this.list = list;
        this.flag = 0;
        this.orderViewSitemanager = orderViewSitemanager;

    }

    public OrderAdapter(DiliverViewSitemanager orderViewSitemanager, Context context, ArrayList<Order> list) {
        Log.d("order", "in in");
        this.context = context;
        this.list = list;
        this.flag = 1;
        this.diliverViewSitemanager = orderViewSitemanager;

    }

    @NonNull
    @Override
    public OrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        Log.d("order", "in");
        if (flag == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_row, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deliver_item_row, parent, false);
        }
        OrderAdapter.MyViewHolder viewHolderClass = new OrderAdapter.MyViewHolder(view);
        return viewHolderClass;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        OrderAdapter.MyViewHolder myViewHolder = (OrderAdapter.MyViewHolder) holder;
        Order order = list.get(position);
        Log.d("order", order.getNumoforderretrive());

        myViewHolder.ref.setText(order.getOrderId());
        myViewHolder.name.setText(order.getProductname() + " -");


        if (flag == 0) {
            myViewHolder.company.setText(order.getSuppliername());
            myViewHolder.cost.setText(order.getTotalbill() + " LKR");
            myViewHolder.date.setText(order.getDate());
            myViewHolder.status.setText("active");
            myViewHolder.qty.setText(order.getNumberofunitorder());
        } else {
            myViewHolder.qty.setText(order.getNumoforderretrive());
        }


        myViewHolder.btn_EditOrder.setVisibility(View.VISIBLE);


        myViewHolder.btn_EditOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    orderViewSitemanager.update(order);
                    orderViewSitemanager.getOrders();
                } else {
                    diliverViewSitemanager.update(order);
                    diliverViewSitemanager.getOrders();
                }
            }
        });


        if (flag == 0) {
            myViewHolder.btn_DeleteOrder.setVisibility(View.VISIBLE);
            myViewHolder.btn_DeleteOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    orderViewSitemanager.delete(order);
                    orderViewSitemanager.getOrders();

                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView ref, name, cost, company, date, qty, status, ConstructionCompanyName;
        private ImageView btn_EditOrder;
        private ImageView btn_DeleteOrder;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ref = itemView.findViewById(R.id.ref);
            name = itemView.findViewById(R.id.name);
            cost = itemView.findViewById(R.id.cost);
            company = itemView.findViewById(R.id.company);
            date = itemView.findViewById(R.id.date);
            qty = itemView.findViewById(R.id.qty);
            status = itemView.findViewById(R.id.status);


            btn_EditOrder = itemView.findViewById(R.id.btn_EditOrder);
            btn_DeleteOrder = itemView.findViewById(R.id.btn_DeleteOrder);
        }
    }


}
