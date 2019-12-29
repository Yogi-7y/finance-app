package com.yogi.financeapp.models;

public class GetQuoteModel {

    private String insuranceName;
    private String name;
    private String email;
    private String phone;

    public GetQuoteModel() {
    }

    public GetQuoteModel(String insuranceName, String name, String email, String phone) {
        this.insuranceName = insuranceName;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
