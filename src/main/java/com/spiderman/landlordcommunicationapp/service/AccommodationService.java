package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;

import java.util.List;

public interface AccommodationService {

    List<Accommodation> getAllAccommodations();

    List<Accommodation> getAllAccommodationsOfThisUser(User user);

    Accommodation save(Accommodation accommodation);

    Accommodation addTenantToThisAccommodation(Accommodation accommodation, User newTenant);

    Accommodation removeTenantFromThisAccommodation(Accommodation accommodation);

    Accommodation getAccommodationByItsId(int id);

    List<Accommodation> getAccommodationByItsUserId(int userId);
}
