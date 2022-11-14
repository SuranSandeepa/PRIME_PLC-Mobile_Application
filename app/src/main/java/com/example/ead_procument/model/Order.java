package com.example.ead_procument.model;

public class Order {

    private String deliverstatus;
    private String numberofunitorder;
    private String numoforderdeliver;
    private String numoforderretrive;
    private String orderstatus;
    private String productname;
    private String purchaseNo;
    private String suppliername;
    private String totalbill;
    private String companyName;
    private String phone;
    private String date;
    private String address;
    private String orderId;
    private String username;

    //order model implemented with getters, setters and constructors
    public Order() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDeliverstatus() {
        return deliverstatus;
    }

    public void setDeliverstatus(String deliverstatus) {
        this.deliverstatus = deliverstatus;
    }

    public String getNumberofunitorder() {
        return numberofunitorder;
    }

    public void setNumberofunitorder(String numberofunitorder) {
        this.numberofunitorder = numberofunitorder;
    }

    public String getNumoforderdeliver() {
        return numoforderdeliver;
    }

    public void setNumoforderdeliver(String numoforderdeliver) {
        this.numoforderdeliver = numoforderdeliver;
    }

    public String getNumoforderretrive() {
        return numoforderretrive;
    }

    public void setNumoforderretrive(String numoforderretrive) {
        this.numoforderretrive = numoforderretrive;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getTotalbill() {
        return totalbill;
    }

    public void setTotalbill(String totalbill) {
        this.totalbill = totalbill;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
