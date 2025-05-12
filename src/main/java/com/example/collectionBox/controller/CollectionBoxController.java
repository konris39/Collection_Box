package com.example.collectionBox.controller;

import com.example.collectionBox.dto.AddMoneyRequest;
import com.example.collectionBox.dto.BoxResponse;
import com.example.collectionBox.service.CollectionBoxService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boxes")
public class CollectionBoxController {

    private final CollectionBoxService boxService;

    public CollectionBoxController(CollectionBoxService boxService) {
        this.boxService = boxService;
    }

    @PostMapping
    public ResponseEntity<BoxResponse> registerBox() {
        BoxResponse resp = boxService.registerBox();
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<List<BoxResponse>> listBoxes() {
        List<BoxResponse> list = boxService.getAllBoxes();
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> unregisterBox(@PathVariable Long id) {
        boxService.unregisterBox(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<BoxResponse> assignBox(
            @PathVariable Long id,
            @RequestParam("eventId") Long eventId) {
        BoxResponse resp = boxService.assignBox(id, eventId);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/{id}/money")
    public ResponseEntity<BoxResponse> addMoney(@PathVariable Long id, @Valid @RequestBody AddMoneyRequest req) {
        BoxResponse resp = boxService.addMoney(id, req);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/{id}/empty")
    public ResponseEntity<BoxResponse> emptyBox(@PathVariable Long id) {
        BoxResponse resp = boxService.emptyBox(id);
        return ResponseEntity.ok(resp);
    }
}
