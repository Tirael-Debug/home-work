package com.sbrf.reboot.repository;

import com.sbrf.reboot.repository.impl.AccountRepositoryException;

import java.io.IOException;
import java.util.Set;

public interface AccountRepository {

    Set<Long> getAllAccountsByClientId(long clientId) throws IOException, AccountRepositoryException;

}
