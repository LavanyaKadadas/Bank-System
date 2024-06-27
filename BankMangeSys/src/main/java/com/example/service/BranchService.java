package com.example.service;

import com.example.dto.BranchDTO;

import java.util.List;

public interface BranchService {
    List<BranchDTO> getAllBranches();
    BranchDTO getBranchById(Integer id);
    BranchDTO saveBranch(BranchDTO branchDTO);
    void softDeleteBranch(Integer id);
}
