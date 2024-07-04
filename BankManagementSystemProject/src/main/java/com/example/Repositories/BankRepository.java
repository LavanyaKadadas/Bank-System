package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entities.Bank;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("SELECT b FROM Bank b WHERE b.deleted = false")
    List<Bank> findAllNotDeleted();

    @Query("SELECT b FROM Bank b WHERE b.id = ?1 AND b.deleted = false")
    Bank findByIdNotDeleted(Long id);
}
