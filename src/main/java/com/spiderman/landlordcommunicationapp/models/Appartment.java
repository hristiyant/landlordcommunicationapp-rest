package com.spiderman.landlordcommunicationapp.models;

import java.sql.Timestamp;

public class Appartment {

    private Location location;
    private String address;
    private User tenant;
    private User landlord;
    private double price;
    private Timestamp dueDate;

}
