package com.chipin.Chiper.sevice.impl;

import com.chipin.Chiper.dto.CustomerDto;
import com.chipin.Chiper.models.Customer;
import com.chipin.Chiper.repository.CustomerRepository;
import com.chipin.Chiper.sevice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapToCustomerDto(customer)).collect(Collectors.toList());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> login(Customer customer) {
        return customerRepository.login(customer.getEmail(), customer.getPassword());
    }

    @Override
    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByFirstName(username);
    }

    private CustomerDto mapToCustomerDto(Customer customer){
        CustomerDto customerDto = CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .accountNUmber(customer.getAccountNUmber())
                .build();

        return customerDto;
    }
}
