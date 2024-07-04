package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entities.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.deleted = false")
    List<Customer> findAllNotDeleted();

    @Query("SELECT c FROM Customer c WHERE c.id = ?1 AND c.deleted = false")
    Customer findByIdNotDeleted(Long id);
}
