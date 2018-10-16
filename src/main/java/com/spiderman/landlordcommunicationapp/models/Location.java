package com.spiderman.landlordcommunicationapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationid")
    private int id;

    @NotNull
    @Column(name = "longitude")
    private double longitude;

    @NotNull
    @Column(name = "latitude")
    private double latitude;

    public Location(){}

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
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

