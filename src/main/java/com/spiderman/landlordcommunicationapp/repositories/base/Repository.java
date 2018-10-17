package com.spiderman.landlordcommunicationapp.repositories.base;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.models.messages.Message;

import java.util.List;

public interface Repository {

    List<Message> getAllMessagesForTheCurrentAccomodation(Accommodation currentAccommodation);

    List<User> getAllLandlords();

    List<Accommodation> getAllAccommodations();

   // void rateThisUser(User user, Rating rating);

    User createUser(User newUser);

    User updateUserById(int id);








}
