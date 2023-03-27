package com.chipin.Chiper.repository;

import com.chipin.Chiper.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String url);
    @Query(value = "SELECT * FROM customer WHERE email = :email AND password = :password", nativeQuery = true)
    Optional<Customer> login(@Param("email")  String email,@Param("password") String password);
}
