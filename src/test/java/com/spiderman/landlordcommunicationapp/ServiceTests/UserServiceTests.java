package com.spiderman.landlordcommunicationapp.ServiceTests;

import com.spiderman.landlordcommunicationapp.models.Rating;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.repositories.RatingRepository;
import com.spiderman.landlordcommunicationapp.repositories.UserRepository;
import com.spiderman.landlordcommunicationapp.service.UserServiceImpl;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    RatingRepository ratingRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    User firstLandlord = new User();

    User firstTenant = new User();

    User secondTenant = new User();

    List<User> listOfAll3Users = new ArrayList<>();

    {
        setDefaultTestUsers(firstLandlord, firstTenant, secondTenant, listOfAll3Users);
    }

    static void setDefaultTestUsers(User firstLandlord, User firstTenant, User secondTenant, List<User> listOfAll3Users) {
        firstLandlord.setId(1);
        firstLandlord.setPhoneNumber("0881223344");
        listOfAll3Users.add(firstLandlord);

        firstTenant.setId(2);
        firstTenant.setPhoneNumber("0883445566");
        listOfAll3Users.add(firstTenant);

        secondTenant.setId(3);
        secondTenant.setPhoneNumber("0884556677");
        listOfAll3Users.add(secondTenant);
    }

    Rating firstRating = new Rating();

    Rating secondRating = new Rating();

    ArrayList<Rating> listOfFirstTenantsRatings = new ArrayList<>();


    {
        setDefaultTestRatings(firstRating, firstLandlord, firstTenant, listOfFirstTenantsRatings, secondRating, secondTenant);
    }

    static void setDefaultTestRatings(Rating firstRating, User firstLandlord, User firstTenant, ArrayList<Rating> listOfFirstTenantsRaitings, Rating secondRating, User secondTenant) {
        firstRating.setRatedUser(firstLandlord);
        firstRating.setSourceUser(firstTenant);
        firstRating.setRating(5.0);
        listOfFirstTenantsRaitings.add(firstRating);

        secondRating.setRatedUser(firstLandlord);
        secondRating.setSourceUser(secondTenant);
        secondRating.setRating(4.0);
        listOfFirstTenantsRaitings.add(secondRating);
    }


    @Test
    public void getAll_ShouldReturnListOfAll3Users() {

        //Arrange
        when(userRepository.findAll())
                .thenReturn(listOfAll3Users);

        //Act
        List<User> result = userService.getAll();

        //Assert
        Assert.assertEquals(result.size(), listOfAll3Users.size());
        Assert.assertEquals(result.get(1), listOfAll3Users.get(1));
    }

    @Test
    public void getAllUsersWhoAreLandlords_ShouldReturnListWithOneUserFirstLandlord() {
        ArrayList<User> landlords = new ArrayList<>();
        landlords.add(firstLandlord);
        when((userRepository.findAllByIsLandlordTrue()))
        .thenReturn(landlords);

        List<User> result = userService.getAllUsersWhoAreLandlords();

        Assert.assertEquals(result.size(), landlords.size());
        Assert.assertEquals(result.get(0), landlords.get(0));
    }

    @Test
    public void getAllUsersWhoAreTenants_ShouldReturnListWithTwoUsersFirstAndSecondTenant() {
        ArrayList<User> tenants = new ArrayList<>();
        tenants.add(firstTenant);
        tenants.add(secondTenant);
        when((userRepository.findAllByIsLandlordFalse()))
                .thenReturn(tenants);

        List<User> result = userService.getAllUsersWhoAreTenants();

        Assert.assertEquals(result.size(), tenants.size());
        Assert.assertEquals(result.get(0), tenants.get(0));
        Assert.assertEquals(result.get(1), tenants.get(1));
    }

    @Test
    public void getUserById_ShouldReturnFirstTenant_WhenCalledWithId2() throws ValidationException {

        when(userRepository.findById(2))
                .thenReturn(firstTenant);

        User result = userService.getUserById(2);

        Assert.assertEquals(result, firstTenant);
    }

    @Test(expected = ValidationException.class)
    public void getUserById_ShouldThrowValidationException_WhenCalledWithId222() throws ValidationException {
        when(userRepository.findById(222))
                .thenReturn(null);

        userService.getUserById(222);
    }

    @Test
    public void getUserByPhoneNumber_ShouldReturnFirstLandlord_WhenCalledWith0881223344() throws ValidationException {
        when(userRepository.findByPhoneNumber("0881223344"))
                .thenReturn(firstLandlord);

        User result = userService.getUserByPhoneNumber("0881223344");

        Assert.assertEquals(result, firstLandlord);
    }

    @Test(expected = ValidationException.class)
    public void getUserByPhoneNumber_ShouldThrowValidationException_WhenCalledWithNonExistingNumber() throws ValidationException {
        when(userRepository.findByPhoneNumber("000088888"))
                .thenReturn(null);

        userService.getUserByPhoneNumber("000088888");
    }



}
