package com.chipin.Chiper.sevice;

import com.chipin.Chiper.dto.TransferFundsDto;
import com.chipin.Chiper.models.TransferFunds;

import java.util.Optional;

public interface TransferFundsService {
    Optional<TransferFunds> isTransferSuccess(TransferFundsDto transferFundsDto);
}
