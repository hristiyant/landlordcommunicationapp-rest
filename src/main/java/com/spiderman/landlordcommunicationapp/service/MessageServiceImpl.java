package com.spiderman.landlordcommunicationapp.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spiderman.landlordcommunicationapp.models.Message;
import com.spiderman.landlordcommunicationapp.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private static final int DAYS_OF_VALIDITY_OF_THE_MESSAGES = 90;
    private final MessageRepository messageRepository;

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

        try {
            FileInputStream serviceAccount = new FileInputStream("D:/JAVA/FinalProject/LandlordCommunicationAppRestAPI/src/main/resources/serviceAccountKey.json");


            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://xperiachat.firebaseio.com")
                    .build();

             FirebaseApp.initializeApp(options);

            FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance();

            defaultDatabase.getReference().child("messagesDatabase").child("tennat1").setValue("newMessage", new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError error, DatabaseReference ref) {

                }
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messageRepository.save(message);
    }

    @Override
    public Message deleteMessage(Message message) {
        message.setDeleted(true);
        messageRepository.save(message);
        return message;
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
    }
    private void deleteMessagesBeforeThisDate(Date date) {
        messageRepository.findAll().stream()
                .filter(x -> x.getTimeSent().before(date) && !x.isDeleted())
                .forEach(x ->
                {
                    x.setDeleted(true);
                    saveMessage(x);
                });
    }
}