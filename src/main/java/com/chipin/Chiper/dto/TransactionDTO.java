package com.chipin.Chiper.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDTO {
    private int transactionID;
    private String accountHolderName;
    private String name;
    private String category;
    private double amount;
    private int points;
    private LocalDateTime timeOfTransaction;
}
