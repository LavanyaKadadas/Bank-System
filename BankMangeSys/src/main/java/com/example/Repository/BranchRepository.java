package com.example.Repository;

import com.example.entities.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends JpaRepository<Branch, Integer> {
    
    @Query("SELECT b FROM Branch b WHERE b.isDeleted = false")
    List<Branch> findAllByIsDeletedFalse();
    
    @Query("SELECT b FROM Branch b WHERE b.branchId = ?1 AND b.isDeleted = false")
    Optional<Branch> findByIdAndIsDeletedFalse(Integer branchId);
}
