package com.chipin.Chiper.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "fundTransfer")
public class TransferFunds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionID;
    private BigDecimal transferAmount;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private String receiveName;
    private String note;
    @CreationTimestamp
    private LocalDateTime createdOn;
}
