package com.example.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.TransactionDTO;
import com.example.Entities.Transaction;
import com.example.ExceptionHandling.ResourceNotFoundException;
import com.example.Repositories.TransactionRepository;

import jakarta.validation.Valid;



@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionDTO createTransaction(@Valid TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(transactionDTO.getAccountId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setType(transactionDTO.getType());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        // Set other properties as needed

        transaction = transactionRepository.save(transaction);

        transactionDTO.setId(transaction.getId());
        return transactionDTO;
    }

    public TransactionDTO getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findByIdNotDeleted(id);
        if (transaction == null) {
            throw new ResourceNotFoundException("Transaction not found");
        }

        return convertToDTO(transaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAllNotDeleted().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteTransaction(Long id) {
        Transaction transaction = transactionRepository.findByIdNotDeleted(id);
        if (transaction == null) {
            throw new ResourceNotFoundException("Transaction not found");
        }

        transaction.setDeleted(true);
        transactionRepository.save(transaction);
    }

    public TransactionDTO updateTransaction(Long id, @Valid TransactionDTO transactionDTO) {
        Transaction existingTransaction = transactionRepository.findByIdNotDeleted(id);
        if (existingTransaction == null) {
            throw new ResourceNotFoundException("Transaction not found");
        }

        existingTransaction.setAccountId(transactionDTO.getAccountId());
        existingTransaction.setAmount(transactionDTO.getAmount());
        existingTransaction.setType(transactionDTO.getType());
        existingTransaction.setTransactionDate(transactionDTO.getTransactionDate());
        // Update other properties as needed

        Transaction updatedTransaction = transactionRepository.save(existingTransaction);

        return convertToDTO(updatedTransaction);
    }

    // Helper method to convert Entity to DTO
    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setAccountId(transaction.getAccountId());
        transactionDTO.setAmount(transaction.getAmount());
        transactionDTO.setType(transaction.getType());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());
        // Map other properties as needed

        return transactionDTO;
    }
}
