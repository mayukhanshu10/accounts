package com.eazybank.accounts.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /*
    In this method we Implement logic to return current Logged In user when Spring Security will be
    implemented. As of now, Hardcoded Value is being Set.
     */

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Account_Microservice");
    }
}
