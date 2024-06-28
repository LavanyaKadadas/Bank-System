package com.example.service;

import com.example.dto.BranchDTO;
import com.example.entities.Branch;
import com.example.exception.ResourceNotFoundException;
import com.example.Repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        branch.setDeleted(true);
        branchRepository.save(branch);
    }

    private BranchDTO convertToDTO(Branch branch) {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchId(branch.getBranchId());
        branchDTO.setName(branch.getName());
        branchDTO.setAddress(branch.getAddress());
        branchDTO.setBankCode(branch.getBank().getCode());
        return branchDTO;
    }

    private Branch convertToEntity(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setBranchId(branchDTO.getBranchId());
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        // Assume bank is already set, otherwise set it here
        return branch;
    }
}
