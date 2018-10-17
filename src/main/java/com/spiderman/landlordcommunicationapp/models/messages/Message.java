package com.spiderman.landlordcommunicationapp.models.messages;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.models.messages.base.Addable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageid")
    private int id;

    @NotNull
    @Column(name = "timesent")
    private Timestamp timeSent;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User tenant;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User landlord;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "accommodationid")
    private Accommodation contextAccommodation;

    @Column(name = "text")
    private String textOfTheMessage;

    @ManyToOne
    @JoinColumn(name = "messageid")
    private Addable addable; //todo how can we use interface here ?
    //https://stackoverflow.com/questions/2912988/persist-collection-of-interface-using-hibernate/2918468#2918468
    //other options?

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextOfTheMessage() {
        return textOfTheMessage;
    }

    public void setTextOfTheMessage(String textOfTheMessage) {
        this.textOfTheMessage = textOfTheMessage;
    }

    public Addable getAddable() {
        return addable;
    }

    public void setAddable(Addable addable) {
        this.addable = addable;
    }

    public Timestamp getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Timestamp timeSent) {
        this.timeSent = timeSent;
    }

    public User getTenant() {
        return tenant;
    }

    public void setTenant(User tenant) {
        this.tenant = tenant;
    }

    public User getLandlord() {
        return landlord;
    }

    public void setLandlord(User landlord) {
        this.landlord = landlord;
    }

    public Accommodation getContextAccommodation() {
        return contextAccommodation;
    }

    public void setContextAccommodation(Accommodation contextAccommodation) {
        this.contextAccommodation = contextAccommodation;
    }
}
