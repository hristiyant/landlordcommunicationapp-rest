package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    List<User> getAllUsersWhoAreTenants();

    List<User> getAllUsersWhoAreLandlords();

    User save(User user);

    User getUserById(int userId);

    User rateUser(User ratedUser, User sourceUser, double rating);
}
