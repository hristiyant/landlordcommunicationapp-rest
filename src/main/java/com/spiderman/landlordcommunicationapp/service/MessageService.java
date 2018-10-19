package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.messages.Message;

import java.util.List;

public interface MessageService {

    List<Message> getMessagesByAccommodationAndIsDeletedFalse(Accommodation accommodation);

    Message saveMessage(Message message);

    Message deleteMessage(Message message);
}
