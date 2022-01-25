package com.sbrf.reboot.service;

import com.sbrf.reboot.exception.AccountException;
import com.sbrf.reboot.repository.AccountRepository;
import com.sbrf.reboot.repository.impl.AccountRepositoryException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.math.BigDecimal;

@AllArgsConstructor
public class AccountService {

    @Getter
    private final AccountRepository repository;

    public boolean isClientHasContract(long clientId, long contractNumber) throws AccountRepositoryException {
        return repository.getContactByClientId(clientId) == contractNumber;
    }

    public BigDecimal getClientAccountBalance(long clientId, long accountNumber) throws AccountException, IOException, AccountRepositoryException {
        if (repository.getAllAccountsByClientId(clientId).contains(accountNumber)) {
            return repository.getAccountBalance(accountNumber);
        }
        throw new AccountException("Unable to get account balance!");
    }
}
