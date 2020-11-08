package com.example.rentmanager.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private Long userId;

    private String userName;

    private String userPassword;

    private String telephoneNumber;

    private String emailAddress;

    @Embedded
    private ArrayList<Residence> residences;

    @Ignore
    public User() {
        this.userName = "";
        this.userPassword = "";
        this.telephoneNumber = "";
        this.emailAddress = "";
    }

    public User(String userName, String userPassword, String telephoneNumber, String emailAddress) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ArrayList<Residence> getResidences() {
        return residences;
    }

    public void setResidences(ArrayList<Residence> residences) {
        this.residences = residences;
    }
}
