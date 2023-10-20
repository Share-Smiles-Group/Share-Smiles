package com.sharesmiles.dto;

public class LoginRequestByUsername {
    private String username;
    private String password;

    // Getters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // Setters
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
