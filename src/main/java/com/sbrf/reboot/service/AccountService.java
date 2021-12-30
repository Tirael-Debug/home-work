package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
public class AccountService {

    @Getter
    private final AccountRepository repository;

    public boolean isClientHasContract(long clientId, long contractNumber) {
        return repository.getContactByClientId(clientId) == contractNumber;
    }

    public BigDecimal getClientAccountBalance(long clientId, long accountNumber) {
        if (repository.getAllAccountsByClientId(clientId).contains(accountNumber)) {
            return repository.getAccountBalance(accountNumber);
        }
        return null;
    }
}
