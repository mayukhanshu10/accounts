package com.eazybank.accounts.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerDto {

    /*
    These Validations are put because these values are coming from Client side, hence a validation should be put
    on fields so that wrong Data or input is not sent by client.
     */

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(min = 5, max= 30, message = "The length of the customer name should be between 5 & 30")
    private String name;
    @NotEmpty(message = "Email can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digit")
    private String mobileNumber;
    private AccountDto accountDto;
}
