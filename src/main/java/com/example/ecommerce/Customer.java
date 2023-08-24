package com.example.ecommerce;

public class Customer {
    private int id,mobile;
    private String name,email;

    public Customer(int id, int mobile, String name, String email) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
