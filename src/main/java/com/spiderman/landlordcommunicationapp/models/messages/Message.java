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

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @NotNull
    @ManyToOne
    @JoinColumn(name =  "contextaccommodation")
    private Accommodation contextAccommodation;

    @Column(name = "text")
    private String textOfTheMessage;

    @ManyToOne
    @JoinColumn(name = "image")
    private ImageAddition image;

    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;

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

    public ImageAddition getImage() {
        return image;
    }

    public void setImage(ImageAddition image) {
        this.image = image;
    }

    public Accommodation getContextAccommodation() {
        return contextAccommodation;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public void setContextAccommodation(Accommodation contextAccommodation) {
        this.contextAccommodation = contextAccommodation;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
