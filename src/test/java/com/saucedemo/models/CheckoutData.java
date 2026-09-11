package com.saucedemo.models;

public class CheckoutData {

    private String firstName;
    private String lastName;
    private String postalCode;
    private String expectedConfirmationMessage;

    public CheckoutData() {
    }

    public CheckoutData(String firstName, String lastName, String postalCode, String expectedConfirmationMessage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
        this.expectedConfirmationMessage = expectedConfirmationMessage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getExpectedConfirmationMessage() {
        return expectedConfirmationMessage;
    }

    public void setExpectedConfirmationMessage(String expectedConfirmationMessage) {
        this.expectedConfirmationMessage = expectedConfirmationMessage;
    }
}