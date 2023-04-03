package com.chipin.Chiper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MpesaTransferDto {
    private int phoneNumber;
    private int amount;
    private String note;
}
