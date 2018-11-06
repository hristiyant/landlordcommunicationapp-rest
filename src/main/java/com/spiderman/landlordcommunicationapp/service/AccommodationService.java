package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;

import java.util.List;

public interface AccommodationService {

    List<Accommodation> getAllAccommodations();

    List<Accommodation> getAllAccommodationsByItsUserId(int userId);

    Accommodation save(Accommodation accommodation);

    Accommodation addTenantToThisAccommodation(Accommodation accommodation, User newTenant);

    Accommodation removeTenantFromThisAccommodation(Accommodation accommodation);

    Accommodation getAccommodationByItsId(int id);

    Accommodation payRentForAccommodation(int id, Accommodation accommodation);

    Accommodation editAccommodationById(int id, Accommodation accommodation);
}
