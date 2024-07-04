package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entities.Branch;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query("SELECT b FROM Branch b WHERE b.deleted = false")
    List<Branch> findAllNotDeleted();

    @Query("SELECT b FROM Branch b WHERE b.id = ?1 AND b.deleted = false")
    Branch findByIdNotDeleted(Long id);
}
