package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Rating;

public interface RatingService {

    double getUserRatingByUserId(int id);

    Rating createAndSaveRating(int ratedUserId, int giverUserId, double ratingValue);
}
