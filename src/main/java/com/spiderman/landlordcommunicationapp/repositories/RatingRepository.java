package com.spiderman.landlordcommunicationapp.repositories;

import com.spiderman.landlordcommunicationapp.models.Rating;
import com.spiderman.landlordcommunicationapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

    List<Rating> findAllByRatedUserId(int ratedUserId);

    Rating save(Rating rating);

    Rating findByRatedUserIdAndSourceUserId(int ratedUserId, int sourceUserId);
}
