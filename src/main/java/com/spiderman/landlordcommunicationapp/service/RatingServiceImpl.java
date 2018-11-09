package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Rating;
import com.spiderman.landlordcommunicationapp.repositories.RatingRepository;
import com.spiderman.landlordcommunicationapp.repositories.UserRepository;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private  final UserRepository userRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public double getUserRatingByUserId(int id) throws ValidationException {
        if (userRepository.findById(id) == null) {
            throw new ValidationException("There is no such user!");
        }

        ArrayList<Double> ratings = new ArrayList<>();
        ratingRepository.findAllByRatedUserId(id)
                .forEach(x -> ratings.add(x.getRating()));
        double average = ratings.stream().mapToDouble(x -> x).average().orElse(0);
        return Math.round(average * 2)/2.0;
    }

    @Override
    public Rating createAndSaveRating(int ratedUserId, int giverUserId, double ratingValue)
            throws ValidationException {
        if (userRepository.findById(ratedUserId) == null) {
            throw new ValidationException("There is no such user to be rated!");
        }
        if (userRepository.findById(giverUserId) == null) {
            throw new ValidationException("There is no such source user!");
        }
        if (ratingValue < 1 || ratingValue > 5) {
            throw new ValidationException("Rating must be between 1 and 5!");
        }
        Rating rating = ratingRepository.findByRatedUserIdAndSourceUserId(ratedUserId, giverUserId);

        if (rating == null) {
            rating = new Rating();
            rating.setRatedUser(userRepository.findById(ratedUserId));
            rating.setSourceUser(userRepository.findById(giverUserId));
        }

        rating.setRating(ratingValue);
        return ratingRepository.save(rating);
    }
}
