package com.chipin.Chiper.controllers;

import com.chipin.Chiper.models.Customer;
import com.chipin.Chiper.models.Transaction;
import com.chipin.Chiper.sevice.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class TransactionController {
    TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/user/login")
    public String getLoginPage(Model model){


        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "login";
    }

    @GetMapping("/user/account")
    public String getAccount(Model model){
        List<Transaction> transactions = transactionService.getAllTransactions();

        if(transactions.isEmpty()) {
            //Don't add to the transaction
        }else{
            model.addAttribute("transactions", transactions);
        }
        return "userPage";
    }

}
