package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.repositories.AccommodationRepository;
import com.spiderman.landlordcommunicationapp.repositories.UserRepository;
import com.spiderman.landlordcommunicationapp.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;
import java.util.Date;

@Service
public class AccommodationServiceImpl implements AccommodationService{
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, UserRepository userRepository) {
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    @Override
    public List<Accommodation> getAllAccommodationsByItsUserId(int userId) throws ValidationException {
        if (userRepository.findById(userId) == null) {
            throw new ValidationException("There is no such user!");
        }

        return  accommodationRepository.findAll().stream()
                .filter(x -> x.getLandlord().getId() == userId || x.getTenant().getId() == userId)
                .collect(Collectors.toList());
    }

    private Accommodation returnAccommodationByIdOrThrowExeption(int accommodationId) throws ValidationException {
        Accommodation accommodation = accommodationRepository.findById(accommodationId);
        if ( accommodation == null) {
            throw new ValidationException("There is no such accommodation!");
        }
        return accommodation;
    }

    @Override
    public Accommodation save(Accommodation accommodation) throws ValidationException {
        if (accommodationRepository.findById(accommodation.getId()) != null) {
            throw new ValidationException("There is already accommodation with same id!");
        }
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation addTenantToThisAccommodation(Accommodation accommodation, User newTenant)
            throws ValidationException{
        if (accommodationRepository.findById(accommodation.getId()) == null) {
            throw new ValidationException("There is no such accommodation!");
        }
        if (userRepository.findById(newTenant.getId()) == null) {
            throw new ValidationException("There is no such user!");
        }

        accommodation.setTenant(newTenant);
        return accommodation;
    }

    @Override
    public Accommodation removeTenantFromThisAccommodation(Accommodation accommodation) throws ValidationException{
        if (accommodationRepository.findById(accommodation.getId()) == null) {
            throw new ValidationException("There is no such accommodation!");
        }

        accommodation.setTenant(null);
        return accommodation;
    }

    @Override
    public Accommodation getAccommodationByItsId(int id) throws ValidationException{
        if (accommodationRepository.findById(id) == null) {
            throw new ValidationException("There is no such accommodation!");
        }
        return accommodationRepository.findById(id);
    }

    @Override
    public Accommodation editAccommodationById(int id, Accommodation accommodation) throws ValidationException{
        if (accommodationRepository.findById(id) == null) {
            throw new ValidationException("There is no such accommodation!");
        }

        return accommodationRepository.save(accommodation);

    }

    @Override
    public Accommodation payRentForAccommodation(int id, Accommodation accommodation) throws ValidationException{
        return accommodationRepository.save(payRent(id));
    }

    private Accommodation payRent(int id) throws ValidationException{

        if (accommodationRepository.findById(id) == null) {
            throw new ValidationException("There is no such accommodation!");
        }

        Accommodation accommodation = accommodationRepository.findById(id);

        LocalDate dateAfterOneMonth = LocalDate.now().plusMonths(1);
        Timestamp dueDate = accommodation.getDueDate();

        if (dateAfterOneMonth.isBefore(dueDate.toLocalDateTime().toLocalDate())) {
            return accommodation; // rent is payed for this month so no changes are needed
        }

        LocalDate nextDueDate = dueDate.toLocalDateTime().toLocalDate().plusMonths(1);
        accommodation.setDueDate(toTimestamp(nextDueDate));
        return accommodation;
    }

    private static Timestamp toTimestamp(LocalDate localDate) {
        Date date = Date.from(localDate.atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant());
        return new Timestamp(date.getTime());
    }
}
