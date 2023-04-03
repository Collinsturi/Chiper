package com.chipin.Chiper.controllers;

import com.chipin.Chiper.models.Customer;
import com.chipin.Chiper.sevice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Objects;
import java.util.Optional;


@Controller
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/index")
    public String geIndex(Model model){
        return "index";
    }



    @PostMapping("/user/login")
    public String login(@ModelAttribute("customer") Customer customer, Authentication authentication, Model model) {
        Optional<Customer> customersMatching = customerService.login(customer);

        try {
            boolean isCustomer = customersMatching.stream()
                    .filter(person -> Objects.equals(person.getEmail(), customer.getEmail()))
                    .anyMatch(person -> Objects.equals(person.getPassword(), customer.getPassword()));

            if (isCustomer) {
                // If the customer is authenticated, set the Authentication object in the SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return "userPage";
            }
            model.addAttribute("errorMessage", "Invalid email or password");

            return "login";
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
    }


    @PostMapping("/user/createAccount")
    public String saveCustomer(@ModelAttribute("customer")Customer customer){
        customerService.saveCustomer(customer);

        return "redirect:/index";
    }



    @GetMapping("/user/payments")
    public String getPayments(Model model){

        return "payment";
    }

    @GetMapping("/user/deposit")
    public String getDeposit(Model model){

        return "deposit";
    }
    @GetMapping("/user/settings")
    public String showSettings(Model model, Authentication authentication) {
        // Get the authenticated user from the security context
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String username = userDetails.getUsername();

        // Fetch the customer information for the authenticated user
        Customer customer = customerService.getCustomerByUsername("John");


        // Add the customer object to the model
        model.addAttribute("customer", customer);

        // Return the name of the template to render
        return "settings";
    }

    @GetMapping("/user/exchanges")
    public String getExchanges(Model model){

        return "exchanges";
    }


    @GetMapping("/user/account/transfer-to-mpesa")
    public String transferFundsToMpesa(Model model){

        return "transferFundsToMpesa";
    }
    @GetMapping("/user/account/get-Estatement")
    public String getEstatement(Model model){

        return "estatement";
    }

//    @GetMapping("/error")
//    public String errorMessage(Model model){
//
//        return "error";
//    }



}
