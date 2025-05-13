package com.example.collectionBox.dto;

import com.example.collectionBox.model.Currency;

public class FinancialReportItem {
    private String eventName;
    private double amount;
    private Currency currency;

    public FinancialReportItem(){}

    public FinancialReportItem(String eventName, double amount, Currency currency) {
        this.eventName = eventName;
        this.amount = amount;
        this.currency = currency;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
