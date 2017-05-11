package com.ss.app.superbiz.model;

import java.util.Date;

public class UserDetails {
    private String id;
    private String mobileNumber;
    private String userId;
    private String name;
    private String email;
    private String password;
    private String dob;
    private boolean agreedTC;
    private String sponsorId;
    private String authtoken;
    private String deviceRegId;
    private String country;
    private String role;
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isAgreedTC() {
        return agreedTC;
    }

    public void setAgreedTC(boolean agreedTC) {
        this.agreedTC = agreedTC;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getDeviceRegId() {
        return deviceRegId;
    }

    public void setDeviceRegId(String deviceRegId) {
        this.deviceRegId = deviceRegId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id='" + id + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                ", agreedTC=" + agreedTC +
                ", sponsorId='" + sponsorId + '\'' +
                ", authtoken='" + authtoken + '\'' +
                ", deviceRegId='" + deviceRegId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
