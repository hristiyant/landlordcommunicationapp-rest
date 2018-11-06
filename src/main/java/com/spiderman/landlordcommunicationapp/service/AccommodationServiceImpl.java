package com.spiderman.landlordcommunicationapp.service;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.User;
import com.spiderman.landlordcommunicationapp.repositories.AccommodationRepository;
import com.spiderman.landlordcommunicationapp.repositories.UserRepository;
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
    public List<Accommodation> getAllAccommodationsByItsUserId(int userId) {

        return  accommodationRepository.findAll().stream()
                .filter(x -> x.getLandlord().getId() == userId || x.getTenant().getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Accommodation save(Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation addTenantToThisAccommodation(Accommodation accommodation, User newTenant) {
        accommodation.setTenant(newTenant);
        return accommodation;
    }

    @Override
    public Accommodation removeTenantFromThisAccommodation(Accommodation accommodation) {
        accommodation.setTenant(null);
        return accommodation;
    }

    @Override
    public Accommodation getAccommodationByItsId(int id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Accommodation editAccommodationById(int id, Accommodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    @Override
    public Accommodation payRentForAccommodation(int id, Accommodation accommodation) {

        accommodation = payRent(id, accommodation);

        return accommodationRepository.save(accommodation);
    }

    private Accommodation payRent(int id, Accommodation accommodation1) {

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
