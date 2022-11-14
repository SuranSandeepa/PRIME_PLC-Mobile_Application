package com.example.ead_procument.services;

public class Calculation {

    public static float totalAmountCal(int qty,String unitPrice){
        return qty*Float.parseFloat(unitPrice);
    }

    public static int deliveredQrt(String now , int already){
        return Integer.parseInt(now)+already;
    }

    public static int retrievedQty(String unit , int del){
        return Integer.parseInt(unit)-del;
    }
}
