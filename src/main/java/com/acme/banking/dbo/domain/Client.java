package com.acme.banking.dbo.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Client {

    private static final int MAX_NAME_LENGTH = 50;

    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if (id <= 0) throw new IllegalArgumentException("Id must be positive");
        if (name == null) throw new IllegalArgumentException("Name couldn't be null");
        if (name.isEmpty() || name.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException("Name length must be between 1 and " + MAX_NAME_LENGTH);

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
