package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;

import java.util.List;

public interface UserService {
    List<User> getAll();

    List<User> getAllUsersWhoAreTenants();

    List<User> getAllUsersWhoAreLandlords();

    User save(User user) throws ValidationException;

    User getUserById(int userId) throws ValidationException;

    User getUserByPhoneNumber(String phoneNumber) throws ValidationException;
}
