package com.chipin.Chiper.sevice;

import com.chipin.Chiper.dto.CustomerDto;
import com.chipin.Chiper.dto.ExchangeRateResponse;
import com.chipin.Chiper.dto.MpesaTransferDto;
import com.chipin.Chiper.models.Customer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDto> findAllCustomers();
    Customer saveCustomer(Customer customer);
    Optional<Customer> login(Customer customer);

    Customer getCustomerByUsername(String username);

    void addToMpesaTransferQueue(MpesaTransferDto mpesaTransfer);

    MpesaTransferDto getConfirmation(MpesaTransferDto mpesaTransferDto);

    Map<String, Double> getExchangeRates();
}
