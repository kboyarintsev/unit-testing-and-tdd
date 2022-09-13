package com.acme.banking.dbo.domain;

public class Cash {
    public void log(double amount, int fromAccountId) {
        System.out.println("Выдано " + amount + " со счёт " + fromAccountId);
    }
}
