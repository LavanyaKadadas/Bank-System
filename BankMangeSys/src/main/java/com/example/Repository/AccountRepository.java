package com.example.Repository;

import com.example.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("SELECT a FROM Account a WHERE a.id = :id AND a.deleted = false")
    Optional<Account> findActiveById(@Param("id") Integer id);

    // Additional repository methods if needed
    List<Account> findAllActive();

     
}
