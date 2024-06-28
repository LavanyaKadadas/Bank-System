package com.example.controller;

import com.example.dto.BranchDTO;
import com.example.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public List<BranchDTO> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Integer id) {
        BranchDTO branchDTO = branchService.getBranchById(id);
        return ResponseEntity.ok(branchDTO);
    }

    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@Valid @RequestBody BranchDTO branchDTO) {
        BranchDTO savedBranch = branchService.saveBranch(branchDTO);
        return ResponseEntity.ok(savedBranch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Integer id) {
        branchService.softDeleteBranch(id);
        return ResponseEntity.noContent().build();
    }
}
