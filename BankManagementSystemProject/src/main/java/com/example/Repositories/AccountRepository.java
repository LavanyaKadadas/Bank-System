package com.example.Repositories;

 
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.deleted = false")
    List<Account> findAllNotDeleted();

    @Query("SELECT a FROM Account a WHERE a.id = ?1 AND a.deleted = false")
    Account findByIdNotDeleted(Long id);
}

