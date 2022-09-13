package com.acme.banking.dbo.repo;

import com.acme.banking.dbo.domain.Client;

public interface ClientRepo {

    void save(Client client);

    Client getById(int clientId);
}
