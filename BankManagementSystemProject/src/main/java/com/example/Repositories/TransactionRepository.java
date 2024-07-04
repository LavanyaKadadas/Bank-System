package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entities.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.deleted = false")
    List<Transaction> findAllNotDeleted();

    @Query("SELECT t FROM Transaction t WHERE t.id = ?1 AND t.deleted = false")
    Transaction findByIdNotDeleted(Long id);
}
