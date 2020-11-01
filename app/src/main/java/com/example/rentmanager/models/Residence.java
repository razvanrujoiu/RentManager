package com.example.rentmanager.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "userId",
        childColumns = "userIdForeignKey",
        onDelete = ForeignKey.CASCADE))
public class Residence {

    @PrimaryKey (autoGenerate = true)
    private Long residenceId;

    private int numberOfRooms;

    private boolean isDetached;

    private double squareFeet;

    private boolean hasBalcony;

    private double constructionYear;

    private double rentalPrice;

    private String endRentalDate;

    @Embedded
    private Address address;

    private Long userIdForeignKey;

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getEndRentalDate() {
        return endRentalDate;
    }

    public void setEndRentalDate(String endRentalDate) {
        this.endRentalDate = endRentalDate;
    }

    public Residence(int numberOfRooms, boolean isDetached, double squareFeet, boolean hasBalcony, double constructionYear, double rentalPrice, String endRentalDate, Address address, Long userIdForeignKey) {
        this.numberOfRooms = numberOfRooms;
        this.isDetached = isDetached;
        this.squareFeet = squareFeet;
        this.hasBalcony = hasBalcony;
        this.constructionYear = constructionYear;
        this.rentalPrice = rentalPrice;
        this.endRentalDate = endRentalDate;
        this.address = address;
        this.userIdForeignKey = userIdForeignKey;
    }

    public Long getResidenceId() {
        return residenceId;
    }

    public void setResidenceId(Long residenceId) {
        this.residenceId = residenceId;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isDetached() {
        return isDetached;
    }

    public void setDetached(boolean detached) {
        isDetached = detached;
    }

    public void setSquareFeet(double squareFeet) {
        this.squareFeet = squareFeet;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }


    public double getSquareFeet() {
        return squareFeet;
    }

    public double getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(double constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getUserIdForeignKey() {
        return userIdForeignKey;
    }

    public void setUserIdForeignKey(Long userIdForeignKey) {
        this.userIdForeignKey = userIdForeignKey;
    }
}