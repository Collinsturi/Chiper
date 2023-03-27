package com.chipin.Chiper.repository;

import com.chipin.Chiper.models.TransferFunds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TransferFundsRepository extends JpaRepository<TransferFunds, Long> {

    @Query(value = "INSERT INTO fundTransfer VALUES(:transferAmount, " +
            ":sourceAccountNumber, :destinationAccountNumber," +
            ":receiveName, :note, :createdOn", nativeQuery = true)
    void createTransaction(@Param("transferAmount") BigDecimal transferAmount, @Param("sourceAccountNumber") String sourceAccountNumber, @Param("destinationAccountNumber") String destinationAccountNumber, @Param("receiveName") String receiveName, @Param("note") String note, @Param("createdOn") LocalDateTime createdOn);

    @Query(value = "SELECT * FROM fundTransfer WHERE destinationAccountNumber = :destinationAccountNumber", nativeQuery = true)
    Optional<TransferFunds> confirmInput(@Param("destinationAccountNumber") String destinationAccountNumber);

}
