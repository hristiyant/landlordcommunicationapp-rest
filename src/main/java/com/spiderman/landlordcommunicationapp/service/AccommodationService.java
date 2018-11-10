package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;

import java.util.List;

public interface AccommodationService {

    List<Accommodation> getAllAccommodations();

    List<Accommodation> getAllAccommodationsByItsUserId(int userId) throws ValidationException;

    Accommodation save(Accommodation accommodation) throws ValidationException;

    Accommodation addTenantToThisAccommodation(int accommodationId, User newTenant) throws ValidationException;

    Accommodation removeTenantFromThisAccommodation(Accommodation accommodation) throws ValidationException;

    Accommodation getAccommodationByItsId(int id) throws ValidationException;

    Accommodation payRentForAccommodation(int id, Accommodation accommodation) throws ValidationException;

    Accommodation editAccommodationById(int id, Accommodation accommodation) throws ValidationException;
}
