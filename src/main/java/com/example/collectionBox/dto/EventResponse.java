package com.example.collectionBox.dto;

import com.example.collectionBox.model.Currency;

public class EventResponse {

    private Long id;
    private String name;
    private Currency currency;
    private long balance;

    public EventResponse() {}

    public EventResponse(Long id, String name, Currency currency, long balance) {
        this.id = id;
        this.name = name;
        this.currency = currency;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }
}
