package com.chipin.Chiper.sevice;

import com.chipin.Chiper.dto.CustomerDto;
import com.chipin.Chiper.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Optional<Transaction> getTransactionsForCustomer(CustomerDto customer);
}
