package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repo.AccountRepo;
import com.acme.banking.dbo.repo.ClientRepo;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Processing {
    private final AtomicInteger clientIdx = new AtomicInteger();

    private final ClientRepo clientRepo;
    private final Cash cash;
    private final AccountRepo accountRepo;

    public Processing(ClientRepo clientRepo, AccountRepo accountRepo, Cash cash) {
        this.clientRepo = clientRepo;
        this.accountRepo = accountRepo;
        this.cash = cash;
    }

    public Client createClient(String name) {
        var client = new Client(clientIdx.incrementAndGet(), name);
        clientRepo.save(client);
        return client;
    }

    public Collection<Account> getAccountsByClientId(int clientId) {
        Client client = Objects.requireNonNull(clientRepo.getById(clientId), "Unknown client id=" + clientId);
        return Collections.unmodifiableCollection(client.getAccounts());
    }

    public void transfer(int fromAccountId, int toAccountId, double amount) {
        //TODO
    }

    public void cash(double amountToWithdraw, int fromAccountId) {
        if (amountToWithdraw < 0) throw new IllegalArgumentException("Amount can't be negative");

        // загрузить счёт
        final Account account = Objects.requireNonNull(
                accountRepo.getById(fromAccountId),
                "Unknown account id=" + fromAccountId
        );

        // уменьшить баланс
        double changedAmount = account.getAmount() - amountToWithdraw;
        if (changedAmount < 0) throw new IllegalArgumentException("Insufficient funds on account id=" + fromAccountId);
        account.setAmount(changedAmount);

        // сохранить счёт
        accountRepo.save(account);

        // напечатать чек
        cash.log(amountToWithdraw, fromAccountId);
    }
}
