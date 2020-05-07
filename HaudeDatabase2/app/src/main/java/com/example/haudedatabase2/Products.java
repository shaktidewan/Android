package com.example.haudedatabase2;

public class Products {
    private int _id;
    private String product_name;

    public Products(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_name() {
        return product_name;
    }
}
