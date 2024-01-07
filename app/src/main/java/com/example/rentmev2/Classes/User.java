package com.example.rentmev2.Classes;

public class User {
    private String email;
    private boolean isRenter;

    public User(String email, boolean isRenter) {
        this.email = email;
        this.isRenter = isRenter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRenter() {
        return isRenter;
    }

    public void setRenter(boolean renter) {
        isRenter = renter;
    }
}
