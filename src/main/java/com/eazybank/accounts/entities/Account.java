package com.eazybank.accounts.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseModel{

//    @GeneratedValue(strategy= GenerationType.IDENTITY) //Will write logic in our Controller to generate AccountNumber.
    @Id
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
    private Long customerId;


}
