package com.example.collectionBox.controller;

import com.example.collectionBox.dto.CreateEventRequest;
import com.example.collectionBox.dto.EventResponse;
import com.example.collectionBox.dto.FinancialReportItem;
import com.example.collectionBox.service.FundraisingEventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class FundraisingEventController {

    private final FundraisingEventService eventService;

    public FundraisingEventController(FundraisingEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @Valid @RequestBody CreateEventRequest req) {
        EventResponse resp = eventService.createEvent(req);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/reports")
    public ResponseEntity<List<FinancialReportItem>> getReport() {
        List<FinancialReportItem> report = eventService.getFinancialReport();
        return ResponseEntity.ok(report);
    }
}
