package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Rating;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;

public interface RatingService {

    double getUserRatingByUserId(int id) throws ValidationException;

    Rating createAndSaveRating(int ratedUserId, int giverUserId, double ratingValue) throws ValidationException;
}
