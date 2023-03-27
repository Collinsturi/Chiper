package com.chipin.Chiper.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long accountNUmber;
   // private String password;
    private String email;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
