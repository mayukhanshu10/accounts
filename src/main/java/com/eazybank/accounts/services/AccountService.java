package com.eazybank.accounts.services;

import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.exceptions.ResourceNotFoundException;

public interface AccountService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber) throws ResourceNotFoundException;

    boolean updateAccount(CustomerDto customerDto) throws ResourceNotFoundException;

    boolean deleteAccount(String mobileNumber) throws ResourceNotFoundException;
}
