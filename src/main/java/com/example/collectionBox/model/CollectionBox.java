package com.example.collectionBox.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "collection_box")
public class CollectionBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private FundraisingEvent assignedEvent;

    @OneToMany(mappedBy = "box", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MoneyEntry> entries = new ArrayList<>();

    public CollectionBox(){}

    public CollectionBox(Long id, FundraisingEvent assignedEvent, List<MoneyEntry> entries) {
        this.id = id;
        this.assignedEvent = assignedEvent;
        this.entries = entries;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FundraisingEvent getAssignedEvent() {
        return assignedEvent;
    }

    public void setAssignedEvent(FundraisingEvent assignedEvent) {
        this.assignedEvent = assignedEvent;
    }

    public List<MoneyEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<MoneyEntry> entries) {
        this.entries = entries;
    }

    public boolean isEmpty(){
        return entries == null || entries.isEmpty();
    }

    public boolean isAssigned(){
        return assignedEvent != null;
    }
}
