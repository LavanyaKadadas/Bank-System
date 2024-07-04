package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entities.Loan;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT l FROM Loan l WHERE l.id = :id AND l.deleted = false")
    Optional<Loan> findByIdNotDeleted(Long id);

    @Query("SELECT l FROM Loan l WHERE l.deleted = false")
    List<Loan> findAllNotDeleted();
}
