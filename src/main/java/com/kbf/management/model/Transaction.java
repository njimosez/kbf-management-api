package com.kbf.management.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id @GeneratedValue
    private Long transactonId;   
    private LocalDate date;
    private String description;
    private double amount;
    private double amountDue;
    private double amountReceived;
    private String reference;
    private String customerName;   
    private TransactionType transType;
    private CategoryType catType;
    
    @JsonIgnore
	  @OneToMany(mappedBy = "transaction",fetch = FetchType.LAZY)
	  private List<FishStock> fishStock;
}