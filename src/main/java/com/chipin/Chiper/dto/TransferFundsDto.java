package com.chipin.Chiper.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferFundsDto {
    private BigDecimal transferAmount;
    private String sourceAccountNumber;
    @NotNull
    private String destinationAccountNumber;
    @NotNull
    private String receiveName;
    private String note;
    @CreationTimestamp
    private LocalDateTime createdOn;
}
