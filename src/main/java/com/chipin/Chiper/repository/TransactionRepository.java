package com.chipin.Chiper.repository;

import com.chipin.Chiper.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM transactions", nativeQuery = true)
    Optional<Transaction> findAllTransactions();

    @Override
    List<Transaction> findAll();

    @Query(value = "SELECT * FROM transactions WHERE accountHolderName = :accountHolderName", nativeQuery = true)
    Optional<Transaction> findByCustomer(@Param("accountHolderName") String customerName);
}
