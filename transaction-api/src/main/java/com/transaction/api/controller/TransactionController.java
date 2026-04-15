package com.transaction.api.controller;

import com.transaction.api.entity.Transaction;
import com.transaction.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    // test endpoint
    @GetMapping("/test")
    public String test() {
        return "API is working with MySQL!";
    }
    
    @GetMapping
    public Map<String, Object> getAllTransactions() {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<Transaction> transactions = transactionService.getAllTransactions();
            
            // Convert transactions to response format
            List<Map<String, Object>> data = new ArrayList<>();
            for (Transaction transaction : transactions) {
                Map<String, Object> transactionMap = new HashMap<>();
                transactionMap.put("id", transaction.getId());
                transactionMap.put("productID", transaction.getProductID());
                transactionMap.put("productName", transaction.getProductName());
                transactionMap.put("amount", transaction.getAmount());
                transactionMap.put("customerName", transaction.getCustomerName());
                transactionMap.put("status", transaction.getStatus());
                transactionMap.put("transactionDate", transaction.getTransactionDate().toString());
                transactionMap.put("createBy", transaction.getCreateBy());
                transactionMap.put("createOn", transaction.getCreateOn().toString());
                data.add(transactionMap);
            }
            
            // Status list
            List<Map<String, Object>> statusList = new ArrayList<>();
            Map<String, Object> status1 = new HashMap<>();
            status1.put("id", 0);
            status1.put("name", "SUCCESS");
            
            Map<String, Object> status2 = new HashMap<>();
            status2.put("id", 1);
            status2.put("name", "FAILED");
            
            statusList.add(status1);
            statusList.add(status2);
            
            response.put("data", data);
            response.put("statusList", statusList);
            response.put("message", "Data retrieved successfully from MySQL");
            
        } catch (Exception e) {
            response.put("error", "Failed to retrieve data: " + e.getMessage());
            response.put("data", new ArrayList<>());
            response.put("statusList", new ArrayList<>());
        }
        
        return response;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getTransaction(@PathVariable Long id) {
        try {
            Optional<Transaction> transaction = transactionService.getTransactionById(id);
            if (transaction.isPresent()) {
                Transaction t = transaction.get();
                Map<String, Object> response = new HashMap<>();
                response.put("id", t.getId());
                response.put("productID", t.getProductID());
                response.put("productName", t.getProductName());
                response.put("amount", t.getAmount());
                response.put("customerName", t.getCustomerName());
                response.put("status", t.getStatus());
                response.put("transactionDate", t.getTransactionDate().toString());
                response.put("createBy", t.getCreateBy());
                response.put("createOn", t.getCreateOn().toString());
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> error = new HashMap<>();
                error.put("error", "Transaction not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to retrieve transaction: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTransaction(@RequestBody Map<String, Object> requestData) {
        try {
            Transaction transaction = new Transaction();
            transaction.setProductID((String) requestData.get("productID"));
            transaction.setProductName((String) requestData.get("productName"));
            transaction.setAmount((String) requestData.get("amount"));
            transaction.setCustomerName((String) requestData.get("customerName"));
            transaction.setStatus(requestData.get("status") != null ? (Integer) requestData.get("status") : 0);
            transaction.setCreateBy((String) requestData.get("createBy"));
            
            Transaction savedTransaction = transactionService.saveTransaction(transaction);
            
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedTransaction.getId());
            response.put("message", "Transaction created successfully");
            response.put("data", savedTransaction);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to create transaction: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateTransaction(@PathVariable Long id, @RequestBody Map<String, Object> requestData) {
        try {
            Transaction transactionDetails = new Transaction();
            transactionDetails.setProductID((String) requestData.get("productID"));
            transactionDetails.setProductName((String) requestData.get("productName"));
            transactionDetails.setAmount((String) requestData.get("amount"));
            transactionDetails.setCustomerName((String) requestData.get("customerName"));
            if (requestData.get("status") != null) {
                transactionDetails.setStatus((Integer) requestData.get("status"));
            }
            
            Transaction updatedTransaction = transactionService.updateTransaction(id, transactionDetails);
            
            Map<String, Object> response = new HashMap<>();
            response.put("id", updatedTransaction.getId());
            response.put("message", "Transaction updated successfully");
            response.put("data", updatedTransaction);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Failed to update transaction: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTransaction(@PathVariable Long id) {
        try {
            transactionService.deleteTransaction(id);
            Map<String, String> result = new HashMap<>();
            result.put("message", "Transaction with ID " + id + " deleted successfully");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Failed to delete transaction: " + e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}