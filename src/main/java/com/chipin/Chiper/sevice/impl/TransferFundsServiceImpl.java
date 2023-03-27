package com.chipin.Chiper.sevice.impl;

import com.chipin.Chiper.dto.TransferFundsDto;
import com.chipin.Chiper.models.TransferFunds;
import com.chipin.Chiper.repository.TransferFundsRepository;
import com.chipin.Chiper.sevice.TransferFundsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferFundsServiceImpl implements TransferFundsService {
    TransferFundsRepository transferFundsRepository;

    public TransferFundsServiceImpl(TransferFundsRepository transferFundsRepository) {
        this.transferFundsRepository = transferFundsRepository;
    }

    @Override
    public Optional<TransferFunds> isTransferSuccess(TransferFundsDto transferFundsDto) {
        transferFundsRepository.createTransaction(transferFundsDto.getTransferAmount(), transferFundsDto.getSourceAccountNumber(),
                transferFundsDto.getDestinationAccountNumber(), transferFundsDto.getReceiveName(),transferFundsDto.getNote(), transferFundsDto.getCreatedOn());

        return transferFundsRepository.confirmInput(transferFundsDto.getDestinationAccountNumber());
    }

    private TransferFunds mapToTransferFunds(TransferFundsDto transferFundsDto) {
        TransferFunds transferFunds = new TransferFunds();
        transferFunds.setTransferAmount(transferFundsDto.getTransferAmount());
        transferFunds.setNote(transferFundsDto.getNote());
        transferFunds.setCreatedOn(transferFundsDto.getCreatedOn());
        transferFunds.setReceiveName(transferFundsDto.getReceiveName());
        transferFunds.setDestinationAccountNumber(transferFundsDto.getDestinationAccountNumber());
        transferFunds.setSourceAccountNumber(transferFundsDto.getSourceAccountNumber());

        return transferFunds;

    }
}
