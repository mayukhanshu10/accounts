package com.eazybank.accounts.repositories;

import com.eazybank.accounts.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findByCustomerId(Long customerId);

    @Transactional // This method is implemented by us, so @Transaction will tell Sprig Data that this method is modifying data so do it in one transaction.
    @Modifying
    void deleteAccountByCustomerId(Long customerId);
}
