package com.acme.banking.dbo.repo;

import com.acme.banking.dbo.domain.Account;

public interface AccountRepo {

    Account getById(int accountId);

    void save(Account account);

}
