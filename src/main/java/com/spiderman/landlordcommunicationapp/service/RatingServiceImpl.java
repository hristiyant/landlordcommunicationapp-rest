package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public double getUserRatingByUserId(int id) {
        ArrayList<Double> ratings = new ArrayList<>();
        ratingRepository.findAllByRatedUserId(id)
                .forEach(x -> ratings.add(x.getRating()));
        double average = ratings.stream().mapToDouble(x -> x).average().orElse(0);
        return Math.round(average * 2)/2.0;
    }
}
