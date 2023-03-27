package com.chipin.Chiper.sevice;

import com.chipin.Chiper.dto.CustomerDto;
import com.chipin.Chiper.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDto> findAllCustomers();
    Customer saveCustomer(Customer customer);
    Optional<Customer> login(Customer customer);

    Customer getCustomerByUsername(String username);
}
