package com.saucedemo.models;

public class LoginData {

    private String username;
    private String password;
    private String expectedErrorMessage;

    // Required by Jackson for deserialization
    public LoginData() {
    }

    public LoginData(String username, String password, String expectedErrorMessage) {
        this.username = username;
        this.password = password;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpectedErrorMessage() {
        return expectedErrorMessage;
    }

    public void setExpectedErrorMessage(String expectedErrorMessage) {
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "username='" + username + '\'' +
                ", expectedErrorMessage='" + expectedErrorMessage + '\'' +
                '}';
    }
}