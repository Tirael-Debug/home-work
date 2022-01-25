package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.repository.AccountRepository;
import lombok.AllArgsConstructor;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class FileAccountRepository implements AccountRepository {

    private final String filePath;

    private static final String CLIENT_ID = "\"clientId\": ";

    private static final String NUMBER = "\"number\": ";

    @Override
    public Set<Long> getAllAccountsByClientId(long clientId) throws IOException, AccountRepositoryException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String line;
        long client = 0L;
        Set<Long> accounts = new HashSet<>();
        try {
            while ((line = reader.readLine()) != null) {
                if (line.indexOf(CLIENT_ID) > 0) {
                    String parsedId = line.split(CLIENT_ID)[1].replaceAll(",", "");
                    client = Long.parseLong(parsedId);
                } else if (line.indexOf(NUMBER) > 0 && client == clientId) {
                    String parsedNum = line.split(NUMBER)[1];
                    accounts.add(Long.parseLong(parsedNum));
                }
            }
        } catch (IOException ex) {
            throw new AccountRepositoryException("Load accounts error!", ex);
        } finally {
            reader.close();
        }
        if (accounts.size() > 0) {
            return accounts;
        } else {
            throw new AccountRepositoryException("Empty accounts data!");
        }
    }

    @Override
    public long getContactByClientId(long clientId) throws AccountRepositoryException {
        throw new AccountRepositoryException("Not implemented!");
    }

    @Override
    public BigDecimal getAccountBalance(long accountId) throws AccountRepositoryException {
        throw new AccountRepositoryException("Not implemented!");
    }
}
