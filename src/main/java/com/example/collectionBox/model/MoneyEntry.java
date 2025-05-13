package com.example.collectionBox.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "money_entry")
public class MoneyEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "box_id", nullable = false)
    private CollectionBox box;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 3, nullable = false)
    private Currency currency;

    @NotNull
    @Column(name = "amount", nullable = false)
    private double amount;

    public MoneyEntry() {}

    public MoneyEntry(Long id, CollectionBox box, Currency currency, double amount) {
        this.id = id;
        this.box = box;
        this.currency = currency;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CollectionBox getBox() {
        return box;
    }

    public void setBox(CollectionBox box) {
        this.box = box;
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
