package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Rating;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.repositories.RatingRepository;
import com.spiderman.landlordcommunicationapp.repositories.UserRepository;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersWhoAreTenants() {
        return userRepository.findAllByIsLandlordFalse();
    }

    @Override
    public List<User> getAllUsersWhoAreLandlords() {
        return userRepository.findAllByIsLandlordTrue();
    }

    @Override
    public User save(User user) throws ValidationException {
        if (userRepository.findById(user.getId()) != null) {
            throw new ValidationException("There is already user with same Id! \n User cannot be saved!");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int userId) throws ValidationException {
        if (userRepository.findById(userId) == null) {
            throw new ValidationException("There is no such user!");
        }
        return userRepository.findById(userId);
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) throws ValidationException {
        User user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new ValidationException("There is no user with such phone number!");
        }
        return user;
    }
}
