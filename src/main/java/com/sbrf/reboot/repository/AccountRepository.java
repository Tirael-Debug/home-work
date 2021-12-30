package com.sbrf.reboot.repository;

import java.io.FileNotFoundException;
import java.util.Set;

public interface AccountRepository {

    Set<Long> getAllAccountsByClientId(long clientId) throws FileNotFoundException;

}
