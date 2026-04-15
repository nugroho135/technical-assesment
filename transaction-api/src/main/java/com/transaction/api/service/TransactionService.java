package com.transaction.api.service;

import com.transaction.api.entity.Transaction;
import com.transaction.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }
    
    public Transaction saveTransaction(Transaction transaction) {
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setCreateOn(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }
    
    public Transaction updateTransaction(Long id, Transaction transactionDetails) {
        Transaction transaction = transactionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        
        if (transactionDetails.getProductID() != null) {
            transaction.setProductID(transactionDetails.getProductID());
        }
        if (transactionDetails.getProductName() != null) {
            transaction.setProductName(transactionDetails.getProductName());
        }
        if (transactionDetails.getAmount() != null) {
            transaction.setAmount(transactionDetails.getAmount());
        }
        if (transactionDetails.getCustomerName() != null) {
            transaction.setCustomerName(transactionDetails.getCustomerName());
        }
        if (transactionDetails.getStatus() != null) {
            transaction.setStatus(transactionDetails.getStatus());
        }
        
        return transactionRepository.save(transaction);
    }
    
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}