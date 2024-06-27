package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Repository.BranchRepository;
import com.example.dto.BranchDTO;
import com.example.entities.Branch;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<BranchDTO> getAllBranches() {
        List<Branch> branches = branchRepository.findAllByIsDeletedFalse();
        return branches.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public BranchDTO getBranchById(Integer id) {
        Branch branch = branchRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));
        return convertToDTO(branch);
    }

    @Override
    public BranchDTO saveBranch(BranchDTO branchDTO) {
        Branch branch = convertToEntity(branchDTO);
        Branch savedBranch = branchRepository.save(branch);
        return convertToDTO(savedBranch);
    }

    @Override
    public void softDeleteBranch(Integer id) {
        Branch branch = branchRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Branch not found with id " + id));
        branch.setIsDeleted(true);
        branchRepository.save(branch);
    }

    private BranchDTO convertToDTO(Branch branch) {
        // Conversion logic from Branch to BranchDTO
    }

    private Branch convertToEntity(BranchDTO branchDTO) {
        // Conversion logic from BranchDTO to Branch
    }
}
