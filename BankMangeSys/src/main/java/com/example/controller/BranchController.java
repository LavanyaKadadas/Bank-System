package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BranchDTO;
import com.example.exception.ResourceNotFoundException;
import com.example.service.BranchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/branches")
@Validated
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public List<BranchDTO> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public BranchDTO getBranchById(@PathVariable Integer id) {
        BranchDTO branchDTO = branchService.getBranchById(id);
        if (branchDTO == null) {
            throw new ResourceNotFoundException("Branch not found with id " + id);
        }
        return branchDTO;
    }

    @PostMapping
    public BranchDTO createBranch(@Valid @RequestBody BranchDTO branchDTO) {
        return branchService.saveBranch(branchDTO);
    }

    @PutMapping("/{id}")
    public BranchDTO updateBranch(@PathVariable Integer id, @Valid @RequestBody BranchDTO branchDTO) {
        BranchDTO existingBranchDTO = branchService.getBranchById(id);
        if (existingBranchDTO == null) {
            throw new ResourceNotFoundException("Branch not found with id " + id);
        }
        branchDTO.setBranchId(id);
        return branchService.saveBranch(branchDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBranch(@PathVariable Integer id) {
        BranchDTO existingBranchDTO = branchService.getBranchById(id);
        if (existingBranchDTO == null) {
            throw new ResourceNotFoundException("Branch not found with id " + id);
        }
        branchService.softDeleteBranch(id);
    }
}
