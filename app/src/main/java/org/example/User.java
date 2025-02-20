package org.example;

public class User {
    private final String username;
    private final String phone;

    public User(String username, String phone) {
        this.username = username;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPhone() {
        return phone;
    }
}
