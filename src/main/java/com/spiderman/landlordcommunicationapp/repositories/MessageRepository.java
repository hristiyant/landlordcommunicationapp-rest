package com.spiderman.landlordcommunicationapp.repositories;

import com.spiderman.landlordcommunicationapp.models.Accommodation;
import com.spiderman.landlordcommunicationapp.models.messages.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByContextAccommodationAndIsDeletedFalse(@NotNull Accommodation contextAccommodation);

    List<Message> findAllByContextAccommodationIdAndIsDeletedFalse(int accommodationId);

    Message findById(int id);

    List<Message> findAll();

    Message save(Message message);

}
