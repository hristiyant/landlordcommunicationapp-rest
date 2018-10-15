package com.spiderman.landlordcommunicationapp.models.messages.base;

import com.spiderman.landlordcommunicationapp.models.Appartment;
import com.spiderman.landlordcommunicationapp.models.User;

import java.sql.Timestamp;

public abstract class Message {

    private Timestamp timeSent;
    private User tenant;
    private User landlord;
    private Appartment contextAppartment;

}
