package com.sbrf.reboot.service;

import com.sbrf.reboot.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);

        accountService = new AccountService(accountRepository);
    }

    @Test
    void contractExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(111L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        when(accountRepository.getContactByClientId(clientId)).thenReturn(contractNumber);

        assertTrue(accountService.isClientHasContract(clientId, contractNumber));
    }

    @Test
    void contractNotExist() {
        Set<Long> accounts = new HashSet();
        accounts.add(222L);

        long clientId = 1L;
        long contractNumber = 111L;

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        when(accountRepository.getContactByClientId(clientId)).thenReturn(222L);

        assertFalse(accountService.isClientHasContract(clientId, contractNumber));
    }

    @Test
    void accountBalanceTestSuccess() {
        long clientId = 1L;
        long accountNumber = 111L;
        BigDecimal accountBalance = new BigDecimal("1000.00");

        Set<Long> accounts = new HashSet();
        accounts.add(accountNumber);

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        when(accountRepository.getAccountBalance(accountNumber)).thenReturn(accountBalance);

        assertEquals(accountService.getClientAccountBalance(clientId, accountNumber), accountBalance);
    }

    @Test
    void accountBalanceTestFails() {
        long clientId = 1L;
        long accountNumber = 111L;

        Set<Long> accounts = new HashSet();
        accounts.add(accountNumber);

        when(accountRepository.getAllAccountsByClientId(clientId)).thenReturn(accounts);

        when(accountRepository.getAccountBalance(accountNumber)).thenReturn(null);

        assertNull(accountService.getClientAccountBalance(clientId, accountNumber));
    }

    @Test
    void repositoryHasTreeMethods() {
        assertEquals(3, AccountRepository.class.getMethods().length);
    }

    @Test
    void serviceHasTreeMethods() {
        assertEquals(3, AccountService.class.getMethods().length - Object.class.getMethods().length);
    }

}