package com.chipin.Chiper.sevice.impl;

import com.chipin.Chiper.dto.CustomerDto;
import com.chipin.Chiper.models.Transaction;
import com.chipin.Chiper.repository.TransactionRepository;
import com.chipin.Chiper.sevice.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionsForCustomer(CustomerDto customerDto) {
        return transactionRepository.findByCustomer(customerDto.getFirstName());
    }
}
