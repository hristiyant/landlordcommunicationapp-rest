package com.spiderman.landlordcommunicationapp.ServiceTests;

import com.spiderman.landlordcommunicationapp.models.Rating;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.repositories.RatingRepository;
import com.spiderman.landlordcommunicationapp.repositories.UserRepository;
import com.spiderman.landlordcommunicationapp.service.RatingServiceImpl;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RatingServiceImplTests {

    @Mock
    RatingRepository ratingRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    RatingServiceImpl ratingService;

    User firstLandlord = new User();

    User firstTenant = new User();

    User secondTenant = new User();

    {
        UserServiceTests.setDefaultTestUsers(firstLandlord, firstTenant, secondTenant, new ArrayList<>());
    }

    Rating firstRating = new Rating();

    Rating secondRating = new Rating();

    ArrayList<Rating> listOfFirstTenantsRaitings = new ArrayList<>();


    {
        UserServiceTests.setDefaultTestRatings(firstRating, firstLandlord, firstTenant, listOfFirstTenantsRaitings, secondRating, secondTenant);
    }

    @Test
    public void getUserRatingByUserId_ShouldReturn_CalculatedRating_WhenCalledWithId_1() throws ValidationException {

        when(userRepository.findById(1))
                .thenReturn(firstLandlord);

        when(ratingRepository.findAllByRatedUserId(1))
                .thenReturn(listOfFirstTenantsRaitings);

        double result =
                ratingService.getUserRatingByUserId(1) - (firstRating.getRating() + secondRating.getRating())/2;

        Assert.assertTrue(result  < 0.01 && result >= 0 );
    }

    @Test(expected = ValidationException.class)
    public void getUserRatingByUserId_ShouldThrowValidationException_WhenCalledWithId_33() throws ValidationException {

        when(userRepository.findById(33))
                .thenReturn(null);

                ratingService.getUserRatingByUserId(33);
    }

    @Test
    public void createAndSaveRating_Should_CallRepositorySaveMethod_WhenCalledWithCorrectParameters() throws ValidationException{

        when(userRepository.findById(secondRating.getRatedUser().getId()))
                .thenReturn(firstLandlord);
        when(userRepository.findById(secondRating.getSourceUser().getId()))
                .thenReturn(firstTenant);

        when(ratingRepository.findByRatedUserIdAndSourceUserId(secondRating.getRatedUser().getId(), secondRating.getSourceUser().getId()))
        .thenReturn(secondRating);

        ratingService.createAndSaveRating(secondRating.getRatedUser().getId(), secondRating.getSourceUser().getId(),
                secondRating.getRating());

        verify(ratingRepository).save(secondRating);

        verify(ratingRepository, times(1)).save(secondRating);
    }


    @Test(expected = ValidationException.class)
    public void createAndSaveRating_ShouldThrowValidationException_WhenCalledNonExistingRatedUserId () throws ValidationException {

        when(userRepository.findById(33))
                .thenReturn(null);

        ratingService.createAndSaveRating(33, 1, 4);
    }

    @Test(expected = ValidationException.class)
    public void createAndSaveRating_ShouldThrowValidationException_WhenCalledNonExistingGiverUserId () throws ValidationException {

        when(userRepository.findById(1))
                .thenReturn(firstLandlord);
        when(userRepository.findById(33))
                .thenReturn(null);

        ratingService.createAndSaveRating(1, 33, 4);
    }


    @Test(expected = ValidationException.class)
    public void createAndSaveRating_ShouldThrowValidationException_WhenCalledWithInvalidRatingValue44 () throws ValidationException {

        when(userRepository.findById(1))
                .thenReturn(firstLandlord);
        when(userRepository.findById(2))
                .thenReturn(firstTenant);

        ratingService.createAndSaveRating(1, 2, 44);
    }
}
