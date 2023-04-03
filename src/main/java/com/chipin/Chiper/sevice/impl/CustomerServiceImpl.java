package com.chipin.Chiper.sevice.impl;

import com.chipin.Chiper.dto.CustomerDto;
import com.chipin.Chiper.dto.ExchangeRateResponse;
import com.chipin.Chiper.dto.MpesaTransferDto;
import com.chipin.Chiper.models.Customer;
import com.chipin.Chiper.models.MpesaTransfer;
import com.chipin.Chiper.repository.CustomerRepository;
import com.chipin.Chiper.sevice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
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

    @Override
    public void addToMpesaTransferQueue(MpesaTransferDto mpesaTransfer) {
        customerRepository.addToMpesaTransferQueue(mpesaTransfer.getPhoneNumber(), mpesaTransfer.getAmount(), mpesaTransfer.getNote());

    }

    @Override
    public MpesaTransferDto getConfirmation(MpesaTransferDto mpesaTransferDto) {
       Optional<MpesaTransfer> mpesaTransfer = customerRepository.searchForTransaction(mpesaTransferDto.getPhoneNumber());

        return mpesaTransfer.map(this::mapToMpesaTransferDto).orElse(null);

    }

    @Override
    public Map<String, Double> getExchangeRates() {
        String apiKey = "7a5a1eaed02e0bc275981d58";
        String url = "https://v6.exchangerate-api.com/v6/7a5a1eaed02e0bc275981d58/latest/USD";

        RestTemplate restTemplate = new RestTemplate();

        ExchangeRateResponse response = restTemplate.getForObject(url, ExchangeRateResponse.class);

        assert response != null;
        return response.getConversionRates();
    }

    private MpesaTransfer mapToMpesaTransfer(MpesaTransferDto mpesaTransferDto){
        return MpesaTransfer.builder()
                .phoneNumber(mpesaTransferDto.getPhoneNumber())
                .note(mpesaTransferDto.getNote())
                .amount(mpesaTransferDto.getAmount())
                .build();
    }

    private MpesaTransferDto mapToMpesaTransferDto(MpesaTransfer mpesaTransfer){
        return MpesaTransferDto.builder()
                .phoneNumber(mpesaTransfer.getPhoneNumber())
                .amount(mpesaTransfer.getAmount())
                .note(mpesaTransfer.getNote())
                .build();
    }

    private CustomerDto mapToCustomerDto(Customer customer){

        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .accountNUmber(customer.getAccountNUmber())
                .build();
    }
}
