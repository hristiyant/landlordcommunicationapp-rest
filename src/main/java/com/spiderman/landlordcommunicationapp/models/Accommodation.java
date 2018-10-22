package com.spiderman.landlordcommunicationapp.models;

import com.spiderman.landlordcommunicationapp.models.messages.Message;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "accommodations")
public class Accommodation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodationid")
    private int id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "location")
    private Location location;

    @NotNull
    @Column(name = "address")
    private String address;

    //todo careful about cycle!
    @ManyToOne
    @JoinColumn(name = "tenant")
    private User tenant;

    @ManyToOne
    @JoinColumn(name = "landlord")
    private User landlord;

    @NotNull
    @Column(name = "price")
    private double price;

    @NotNull
    @Column(name = "duedate")
    private Timestamp dueDate;

    @OneToMany
    @JoinColumn(name = "contextAccomodation")
    private List<Message> messages;

    public void setId(int id) {
        this.id = id;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Location getLocation() {
        return location;
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
