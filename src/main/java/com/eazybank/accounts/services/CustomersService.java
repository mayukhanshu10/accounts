package com.eazybank.accounts.services;


import com.eazybank.accounts.dto.CustomerDetailsDto;
import com.eazybank.accounts.exceptions.ResourceNotFoundException;

public interface CustomersService {

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber) throws ResourceNotFoundException;
}
