package com.eazybank.accounts.services;

import com.eazybank.accounts.constants.AccountConstants;
import com.eazybank.accounts.dtos.AccountDto;
import com.eazybank.accounts.dtos.CustomerDto;
import com.eazybank.accounts.entities.Account;
import com.eazybank.accounts.entities.Customer;
import com.eazybank.accounts.exceptions.CustomerAlreadyExistsException;
import com.eazybank.accounts.exceptions.ResourceNotFoundException;
import com.eazybank.accounts.mapper.AccountMapper;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repositories.AccountRepository;
import com.eazybank.accounts.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) throws ResourceNotFoundException {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","CustomerId",customer.getId().toString())
        );

        /*
            Now to send Customer Data and Account data we have two options:
            1) Either Create 1 more Dto containing both datas OR
            2) Insert AccountDto in CustomerDto class, so that output will have both the data.
        */

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account,new AccountDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) throws ResourceNotFoundException {

        boolean isUpdated= false;
        AccountDto accountDto = customerDto.getAccountDto();
        if(accountDto!=null){
            Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    ()->new ResourceNotFoundException("Account","AccountNumber",accountDto.getAccountNumber().toString())
            );

            AccountMapper.mapToAccount(accountDto,account);
            account=accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer= customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer","CustomerId",customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) throws ResourceNotFoundException {
        Customer customer= customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber)
        );

        /*
         Hard Deleteing as of now. Implement Soft delete in future
         */
        accountRepository.deleteAccountByCustomerId(customer.getId());
        customerRepository.deleteById(customer.getId());
        return true;
    }
}
