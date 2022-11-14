package com.example.ead_procument.listiner;


import com.example.ead_procument.model.Order;

public interface OnOrderClicked {
    public void orderClick(Order order);

    public void orderDeleteClick(Order order);

}
