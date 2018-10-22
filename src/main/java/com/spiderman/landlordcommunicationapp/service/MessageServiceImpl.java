package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.messages.Message;
import com.spiderman.landlordcommunicationapp.repositories.MessageRepository;
import com.spiderman.landlordcommunicationapp.service.utils.DeleteTimeManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private DeleteTimeManagement deleteTimeManagement;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getMessagesByAccommodationAndIsDeletedFalse(Accommodation accommodation) {

        Date currentDate = new Date();
        //todo https://www.joda.org/joda-time/ for 3 months back!
        if (deleteTimeManagement == null) {
            deleteTimeManagement = new DeleteTimeManagement();
            deleteTimeManagement.setDateOfLastDelete(currentDate);
            deleteMessagesBeforeThisDate(currentDate);
        }

        if (deleteTimeManagement.getDateOfLastDelete().before(currentDate)) {
            deleteMessagesBeforeThisDate(currentDate);
            deleteTimeManagement.setDateOfLastDelete(currentDate);
        }

        return messageRepository.findAllByContextAccommodationAndIsDeletedFalse(accommodation);
    }

    private void deleteMessagesBeforeThisDate(Date date) {
        // List<Message> allMessages = messageRepository.findAll();
        messageRepository.findAll().stream()
                .filter(x -> x.getTimeSent().before(date)&&x.isDeleted())
                .forEach(x ->
                {
                    x.setDeleted(true);
                    saveMessage(x);
                });
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message deleteMessage(Message message) {
        message.setDeleted(true);
        return messageRepository.save(message);
    }
}
