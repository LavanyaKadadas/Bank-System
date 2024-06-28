package com.example.Repository;

import com.example.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    List<Loan> findAllByIsDeletedFalse();

    Optional<Loan> findByIdAndIsDeletedFalse(Integer id);

    // You can add more custom queries if needed
}
