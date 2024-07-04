package com.example.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.BranchDTO;
import com.example.Entities.Bank;
import com.example.Entities.Branch;
import com.example.ExceptionHandling.ResourceNotFoundException;
import com.example.Repositories.BankRepository;
import com.example.Repositories.BranchRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BankRepository bankRepository;

    public BranchDTO createBranch(BranchDTO branchDTO) {
        Bank bank = bankRepository.findByIdNotDeleted(branchDTO.getBankId());
        if (bank == null) {
            throw new ResourceNotFoundException("Bank not found");
        }

        Branch branch = new Branch();
        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setBank(bank);

        branch = branchRepository.save(branch);

        branchDTO.setId(branch.getId());
        return branchDTO;
    }

    public BranchDTO getBranchById(Long id) {
        Branch branch = branchRepository.findByIdNotDeleted(id);
        if (branch == null) {
            throw new ResourceNotFoundException("Branch not found");
        }

        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setId(branch.getId());
        branchDTO.setName(branch.getName());
        branchDTO.setAddress(branch.getAddress());
        branchDTO.setBankId(branch.getBank().getId());

        return branchDTO;
    }

    public List<BranchDTO> getAllBranches() {
        return branchRepository.findAllNotDeleted().stream()
                .map(branch -> {
                    BranchDTO branchDTO = new BranchDTO();
                    branchDTO.setId(branch.getId());
                    branchDTO.setName(branch.getName());
                    branchDTO.setAddress(branch.getAddress());
                    branchDTO.setBankId(branch.getBank().getId());
                    return branchDTO;
                })
                .collect(Collectors.toList());
    }

    public void deleteBranch(Long id) {
        Branch branch = branchRepository.findByIdNotDeleted(id);
        if (branch == null) {
            throw new ResourceNotFoundException("Branch not found");
        }

        branch.setDeleted(true);
        branchRepository.save(branch);
    }

    public BranchDTO updateBranch(Long id, BranchDTO branchDTO) {
        Branch branch = branchRepository.findByIdNotDeleted(id);
        if (branch == null) {
            throw new ResourceNotFoundException("Branch not found");
        }

        Bank bank = bankRepository.findByIdNotDeleted(branchDTO.getBankId());
        if (bank == null) {
            throw new ResourceNotFoundException("Bank not found");
        }

        branch.setName(branchDTO.getName());
        branch.setAddress(branchDTO.getAddress());
        branch.setBank(bank);

        branch = branchRepository.save(branch);

        return branchDTO;
    }
}
