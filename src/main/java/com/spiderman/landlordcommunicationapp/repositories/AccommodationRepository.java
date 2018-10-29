package com.spiderman.landlordcommunicationapp.repositories;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Integer> {

    List<Accommodation> findAll();

    Accommodation save(Accommodation accommodation);

    Accommodation findById(int id);

    List<Accommodation> findAllByLandlordId(int landlordId);

    List<Accommodation> findAllByTenantId(int tenantId);
}
