package com.example.collectionBox.dto;

import com.example.collectionBox.model.Currency;
import jakarta.validation.constraints.NotNull;

public class AddMoneyRequest {
    @NotNull
    private Currency currency;
    private double amount;

    public AddMoneyRequest(){}

    public AddMoneyRequest(Currency currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
