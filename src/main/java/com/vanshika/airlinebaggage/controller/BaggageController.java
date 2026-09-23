package com.vanshika.airlinebaggage.controller;

import com.vanshika.airlinebaggage.entity.Baggage;
import com.vanshika.airlinebaggage.service.BaggageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baggage")
public class BaggageController {

    private final BaggageService baggageService;

    public BaggageController(BaggageService baggageService) {
        this.baggageService = baggageService;
    }

    @PostMapping
    public Baggage createBaggage(@Valid @RequestBody Baggage baggage) {
        return baggageService.saveBaggage(baggage);
    }

    @GetMapping
    public List<Baggage> getAllBaggage() {
        return baggageService.getAllBaggage();
    }

    @GetMapping("/{id}")
    public Baggage getBaggageById(@PathVariable Long id) {
        return baggageService.getBaggageById(id);
    }

    @PutMapping("/{id}")
    public Baggage updateBaggage(
            @PathVariable Long id,
            @Valid @RequestBody Baggage baggage) {

        return baggageService.updateBaggage(id, baggage);
    }

    @DeleteMapping("/{id}")
    public void deleteBaggage(@PathVariable Long id) {
        baggageService.deleteBaggage(id);
    }
}