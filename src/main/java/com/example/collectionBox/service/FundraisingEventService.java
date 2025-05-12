package com.example.collectionBox.service;

import com.example.collectionBox.dto.CreateEventRequest;
import com.example.collectionBox.dto.EventResponse;
import com.example.collectionBox.dto.FinancialReportItem;
import com.example.collectionBox.model.FundraisingEvent;
import com.example.collectionBox.repository.FundraisingEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FundraisingEventService {

    private final FundraisingEventRepository eventRepo;

    public FundraisingEventService(FundraisingEventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    public EventResponse createEvent(CreateEventRequest req) {
        FundraisingEvent ev = new FundraisingEvent();
        ev.setName(req.getName());
        ev.setCurrency(req.getCurrency());
        ev.setBalance(0L);
        ev = eventRepo.save(ev);
        return new EventResponse(ev.getId(), ev.getName(), ev.getCurrency(), ev.getBalance());
    }

    public List<FinancialReportItem> getFinancialReport() {
        return eventRepo.findAll().stream()
            .map(ev -> new FinancialReportItem(ev.getName(), ev.getBalance(), ev.getCurrency()))
            .collect(Collectors.toList());
    }
}
