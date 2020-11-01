package com.example.rentmanager.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Residence.class,
        parentColumns = "residenceId",
        childColumns = "residenceIdForeignKey",
        onDelete = ForeignKey.CASCADE))
public class Address {

    @PrimaryKey(autoGenerate = true)
    private Long addressId = 0L;

    private String streetName;

    private String number;

    private String postalCode;

    private String city;

    private String country;

    private Long residenceIdForeignKey;

    public Address(String streetName, String number, String postalCode, String city, String country) {
        this.streetName = streetName;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Address(String streetName, String number, String postalCode, String city, String country, Long residenceIdForeignKey) {
        this.streetName = streetName;
        this.number = number;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.residenceIdForeignKey = residenceIdForeignKey;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getResidenceIdForeignKey() {
        return residenceIdForeignKey;
    }

    public void setResidenceIdForeignKey(Long residenceIdForeignKey) {
        this.residenceIdForeignKey = residenceIdForeignKey;
    }
}
