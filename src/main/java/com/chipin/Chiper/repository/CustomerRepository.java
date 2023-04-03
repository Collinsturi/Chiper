package com.chipin.Chiper.repository;

import com.chipin.Chiper.dto.MpesaTransferDto;
import com.chipin.Chiper.models.Customer;
import com.chipin.Chiper.models.MpesaTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String url);
    @Query(value = "SELECT * FROM customer WHERE email = :email AND password = :password", nativeQuery = true)
    Optional<Customer> login(@Param("email")  String email,@Param("password") String password);
    @Query(value = "INSERT INTO MpesaTransfer(phoneNumber, amount, note) VALUES (:phoneNumber, :amount, :note", nativeQuery = true)
    void addToMpesaTransferQueue(@Param("phoneNumber")int phoneNumber,@Param("amount") int amount,@Param("note") String note);
    @Query(value = "SELECT * FROM MpesaTransfer WHERE phoneNumber = :phoneNumber", nativeQuery = true)
    Optional<MpesaTransfer> searchForTransaction(@Param("phoneNumber")int phoneNumber);

}
