package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User save(User user);

    User rateUser(User ratedUser, User sourceUser, double rating);
}
