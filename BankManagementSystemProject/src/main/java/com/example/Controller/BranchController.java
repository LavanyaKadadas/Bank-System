package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.DTO.BranchDTO;
import com.example.Services.BranchService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @PostMapping
    public ResponseEntity<?> createBranch(@Valid @RequestBody BranchDTO branchDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(branchService.createBranch(branchDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable Long id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBranch(@PathVariable Long id, @Valid @RequestBody BranchDTO branchDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(branchService.updateBranch(id, branchDTO));
    }
}
