package com.spiderman.landlordcommunicationapp.models.messages;

import com.spiderman.landlordcommunicationapp.models.Accommodation;

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

    @Column(name = "isSenderLandlord")
    private boolean isSenderLandlord;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @NotNull
    @ManyToOne
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

    public boolean isSenderLandlord() {
        return isSenderLandlord;
    }

    public void setSenderLandlord(boolean senderLandlord) {
        isSenderLandlord = senderLandlord;
    }
}
