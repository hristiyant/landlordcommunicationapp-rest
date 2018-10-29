package com.spiderman.landlordcommunicationapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "accommodations")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodationid")
    private int id;

    @NotNull
    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "tenant")
    private User tenant;

    @ManyToOne
    @JoinColumn(name = "landlord")
    private User landlord;

    @NotNull
    @Column(name = "price")
    private double price;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @NotNull
    @Column(name = "duedate")
    private Timestamp dueDate;

    @Column(name = "duelastsentdate")
    private Timestamp dueLastSentDate;

    public Accommodation() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public User getTenant() {
        return tenant;
    }

    public User getLandlord() {
        return landlord;
    }

    public double getPrice() {
        return price;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public Timestamp getDueLastSentDate() {
        return dueLastSentDate;
    }

    public void setDueLastSentDate(Timestamp dueLastSentDate) {
        this.dueLastSentDate = dueLastSentDate;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
