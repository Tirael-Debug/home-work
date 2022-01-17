package com.sbrf.reboot.repository;

import com.sbrf.reboot.repository.impl.AccountRepositoryException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

public interface AccountRepository {

    Set<Long> getAllAccountsByClientId(long clientId) throws IOException, AccountRepositoryException;

    long getContactByClientId(long clientId) throws AccountRepositoryException;

    BigDecimal getAccountBalance(long accountId) throws AccountRepositoryException;
}
