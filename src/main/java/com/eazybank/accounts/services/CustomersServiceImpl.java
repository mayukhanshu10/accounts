package com.eazybank.accounts.services;

import com.eazybank.accounts.clients.CardsFeignClient;
import com.eazybank.accounts.clients.LoansFeignClient;
import com.eazybank.accounts.dto.AccountDto;
import com.eazybank.accounts.dto.CardsDto;
import com.eazybank.accounts.dto.CustomerDetailsDto;
import com.eazybank.accounts.dto.LoansDto;
import com.eazybank.accounts.entities.Account;
import com.eazybank.accounts.entities.Customer;
import com.eazybank.accounts.exceptions.ResourceNotFoundException;
import com.eazybank.accounts.mapper.AccountMapper;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repositories.AccountRepository;
import com.eazybank.accounts.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements CustomersService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
