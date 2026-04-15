package com.transaction.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "product_id")
    private String productID;
    
    @Column(name = "product_name")
    private String productName;
    
    @Column(name = "amount")
    private String amount;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
    
    @Column(name = "create_by")
    private String createBy;
    
    @Column(name = "create_on")
    private LocalDateTime createOn;
    
   
    public Transaction() {}
    
  
    public Transaction(String productID, String productName, String amount, 
                      String customerName, Integer status, String createBy) {
        this.productID = productID;
        this.productName = productName;
        this.amount = amount;
        this.customerName = customerName;
        this.status = status;
        this.createBy = createBy;
        this.transactionDate = LocalDateTime.now();
        this.createOn = LocalDateTime.now();
    }
    
   
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getProductID() { return productID; }
    public void setProductID(String productID) { this.productID = productID; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    
    public String getCreateBy() { return createBy; }
    public void setCreateBy(String createBy) { this.createBy = createBy; }
    
    public LocalDateTime getCreateOn() { return createOn; }
    public void setCreateOn(LocalDateTime createOn) { this.createOn = createOn; }
}