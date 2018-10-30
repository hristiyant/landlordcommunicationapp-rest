package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.Message;
import com.spiderman.landlordcommunicationapp.repositories.MessageRepository;
import com.spiderman.landlordcommunicationapp.service.utils.DeleteTimeManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private static final int DAYS_OF_VALIDITY_OF_THE_MESSAGES = 90;
    private final MessageRepository messageRepository;
    private DeleteTimeManagement deleteTimeManagement;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getMessagesByAccommodationIdAndIsDeletedFalse(int accommodationId) {
        return messageRepository.findAllByContextAccommodationIdAndIsDeletedFalse(accommodationId);
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

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public void markAllMessagesOlderThan3MonthsAsDeleted() {
        Instant now = Instant.now();
        Instant before3Months = now.minus(Duration.ofDays(DAYS_OF_VALIDITY_OF_THE_MESSAGES));
        Date dateThreeMonthsFromNow = Date.from(before3Months);

        deleteMessagesBeforeThisDate(dateThreeMonthsFromNow);


//        if (deleteTimeManagement == null) {
//            deleteTimeManagement = new DeleteTimeManagement();
//            deleteTimeManagement.setDateOfLastDelete(dateThreeMonthsFromNow);
//            deleteMessagesBeforeThisDate(dateThreeMonthsFromNow);
//        }
//
//        if (deleteTimeManagement.getDateOfLastDelete().before(dateThreeMonthsFromNow)) {
//            deleteMessagesBeforeThisDate(dateThreeMonthsFromNow);
//            deleteTimeManagement.setDateOfLastDelete(dateThreeMonthsFromNow);
//        }
//
    }
    private void deleteMessagesBeforeThisDate(Date date) {
        messageRepository.findAll().stream()
                .filter(x -> x.getTimeSent().before(date) && x.isDeleted() == false)
                .forEach(x ->
                {
                    x.setDeleted(true);
                    saveMessage(x);
                });
    }


}
