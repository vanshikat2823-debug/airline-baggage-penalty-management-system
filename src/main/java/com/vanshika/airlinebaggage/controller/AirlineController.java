package com.vanshika.airlinebaggage.controller;

import com.vanshika.airlinebaggage.entity.Airline;
import com.vanshika.airlinebaggage.service.AirlineService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @PostMapping
    public Airline createAirline(@Valid @RequestBody Airline airline) {
        return airlineService.saveAirline(airline);
    }

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public Airline getAirlineById(@PathVariable Long id) {
        return airlineService.getAirlineById(id);
    }

    @PutMapping("/{id}")
    public Airline updateAirline(
            @PathVariable Long id,
            @Valid @RequestBody Airline airline) {

        return airlineService.updateAirline(id, airline);
    }

    @DeleteMapping("/{id}")
    public void deleteAirline(@PathVariable Long id) {
        airlineService.deleteAirline(id);
    }
}