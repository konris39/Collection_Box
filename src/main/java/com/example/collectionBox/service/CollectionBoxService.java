package com.example.collectionBox.service;

import com.example.collectionBox.dto.AddMoneyRequest;
import com.example.collectionBox.dto.BoxResponse;
import com.example.collectionBox.model.CollectionBox;
import com.example.collectionBox.model.Currency;
import com.example.collectionBox.model.FundraisingEvent;
import com.example.collectionBox.model.MoneyEntry;
import com.example.collectionBox.repository.CollectionBoxRepository;
import com.example.collectionBox.repository.FundraisingEventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CollectionBoxService {

    private final CollectionBoxRepository boxRepo;
    private final FundraisingEventRepository eventRepo;

    private static final Map<Currency, Map<Currency, Double>> RATES = Map.of(
        Currency.EUR, Map.of(Currency.EUR, 1.0, Currency.USD, 1.13, Currency.PLN, 4.23),
        Currency.USD, Map.of(Currency.EUR, 0.89, Currency.USD, 1.0, Currency.PLN, 3.76),
        Currency.PLN, Map.of(Currency.EUR, 0.24, Currency.USD, 0.27, Currency.PLN, 1.0)
    );

    public CollectionBoxService(CollectionBoxRepository boxRepo, FundraisingEventRepository eventRepo) {
        this.boxRepo = boxRepo;
        this.eventRepo = eventRepo;
    }

    public BoxResponse registerBox() {
        CollectionBox box = new CollectionBox();
        box = boxRepo.save(box);
        return toDto(box);
    }

    public List<BoxResponse> getAllBoxes() {
        return boxRepo.findAll().stream()
                      .map(this::toDto)
                      .collect(Collectors.toList());
    }

    public void unregisterBox(Long boxId) {
        CollectionBox box = boxRepo.findById(boxId)
            .orElseThrow(() -> new NoSuchElementException("Box not found"));
        box.getEntries().clear();
        boxRepo.delete(box);
    }

    public BoxResponse assignBox(Long boxId, Long eventId) {
        CollectionBox box = boxRepo.findById(boxId)
            .orElseThrow(() -> new NoSuchElementException("Box not found"));
        if (!box.isEmpty()) {
            throw new IllegalStateException("Box must be empty to assign");
        }
        FundraisingEvent ev = eventRepo.findById(eventId)
            .orElseThrow(() -> new NoSuchElementException("Event not found"));

        box.setAssignedEvent(ev);
        box = boxRepo.save(box);
        return toDto(box);
    }

    public BoxResponse addMoney(Long boxId, AddMoneyRequest req) {
        CollectionBox box = boxRepo.findById(boxId)
            .orElseThrow(() -> new NoSuchElementException("Box not found"));

        double amount = req.getAmount();

        double scaled = amount * 100;
        if (Math.floor(scaled) != scaled) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "To many decimal places, maximum is 2"
            );
        }

        MoneyEntry entry = box.getEntries().stream()
            .filter(e -> e.getCurrency() == req.getCurrency())
            .findFirst()
            .orElseGet(() -> {
                MoneyEntry newEntry = new MoneyEntry();
                newEntry.setBox(box);
                newEntry.setCurrency(req.getCurrency());
                box.getEntries().add(newEntry);
                return newEntry;
            });

        entry.setAmount(entry.getAmount() + amount);
        boxRepo.save(box);

        return toDto(box);
    }

    public BoxResponse emptyBox(Long boxId) {
        CollectionBox box = boxRepo.findById(boxId)
            .orElseThrow(() -> new NoSuchElementException("Box not found"));
        FundraisingEvent ev = box.getAssignedEvent();
        if (ev == null) {
            throw new IllegalStateException("Box is not assigned to any event");
        }

        double rawSum = box.getEntries().stream()
            .mapToDouble(entry -> {
                double rate = RATES.get(entry.getCurrency()).get(ev.getCurrency());
                return entry.getAmount() * rate;
            }).sum();

        double totalConverted = Math.round(rawSum * 100.0) / 100.0;

        ev.setBalance(ev.getBalance() + totalConverted);
        eventRepo.save(ev);

        box.getEntries().clear();
        box = boxRepo.save(box);

        return toDto(box);
    }

    private BoxResponse toDto(CollectionBox box) {
        return new BoxResponse(box.getId(), box.isAssigned(), box.isEmpty());
    }
}
