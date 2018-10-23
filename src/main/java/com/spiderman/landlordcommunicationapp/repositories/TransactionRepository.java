package com.spiderman.landlordcommunicationapp.repositories;

import com.spiderman.landlordcommunicationapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    Transaction save(Transaction transaction);

    List<Transaction> findAll();
}
