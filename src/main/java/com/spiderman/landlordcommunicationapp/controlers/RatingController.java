package com.spiderman.landlordcommunicationapp.controlers;

import com.spiderman.landlordcommunicationapp.models.Rating;
import com.spiderman.landlordcommunicationapp.service.RatingService;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Ratings")
@PreAuthorize("isAnonymous()")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PutMapping("/{ratedUserId}/{giverUserId}/{ratingValue}")
    public Rating rateUser(@PathVariable int ratedUserId, @PathVariable int giverUserId,
                           @PathVariable double ratingValue) throws ValidationException {
        return ratingService.createAndSaveRating(ratedUserId, giverUserId, ratingValue);
    }
}
