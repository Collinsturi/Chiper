package com.chipin.Chiper.controllers;

import com.chipin.Chiper.dto.CustomerDto;
import com.chipin.Chiper.dto.TransferFundsDto;
import com.chipin.Chiper.sevice.TransferFundsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class TransferFundsController {
    TransferFundsService transferFundsService;

    public TransferFundsController(TransferFundsService transferFundsService) {
        this.transferFundsService = transferFundsService;
    }

    @GetMapping("/user/account/transfer")
    public String transferFunds(Model model){
        TransferFundsDto transferFundsDto = new TransferFundsDto();

        model.addAttribute("transferFundsDto", transferFundsDto);

        return "transferFunds";
    }
    @PostMapping("/user/account/transfer")
    public String moveFunds(@ModelAttribute("transferFundsDto") TransferFundsDto transferFundsDto){
        boolean isTransferSuccess = transferFundsService.isTransferSuccess(transferFundsDto).stream()
                .filter(person -> Objects.equals(person.getDestinationAccountNumber(), transferFundsDto.getDestinationAccountNumber()))
                .anyMatch(person -> Objects.equals(person.getDestinationAccountNumber(), transferFundsDto.getDestinationAccountNumber()));


        if(isTransferSuccess){
            return "transferFundsConfirmation";
        }

        return "transferFundsFailed";
    }

    @GetMapping("/user/account/transfer/transfer-confirmation")
    public String confirmFundsTransfer(Model model){

        return "transferFundsConfirmation";
    }
    @GetMapping("/user/account/transfer/transfer-failed")
    public String failesFundsTransfer(Model model){

        return "transferFundsFailed";
    }

}
