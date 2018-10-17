package com.spiderman.landlordcommunicationapp.models.messages;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;

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
    @JoinColumn(name = "tenant")
    private User tenant;

    @ManyToOne
    @JoinColumn(name = "landlord")
    private User landlord;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "accommodation")
    private Accommodation contextAccommodation;

    @Column(name = "text")
    private String textOfTheMessage;

    @ManyToOne
    @JoinColumn(name = "image")
    private ImageAddition image;

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

    public ImageAddition getImage() {
        return image;
    }

    public void setImage(ImageAddition image) {
        this.image = image;
    }
}
