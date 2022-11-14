package com.example.ead_procument.model;

public class Supplier {
    private String contactno;
    private String supplieraddress;
    private String suppliername;

    //supplier model implemented with getters, setters and constructors
    public Supplier() {

    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getSupplieraddress() {
        return supplieraddress;
    }

    public void setSupplieraddress(String supplieraddress) {
        this.supplieraddress = supplieraddress;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }
}
