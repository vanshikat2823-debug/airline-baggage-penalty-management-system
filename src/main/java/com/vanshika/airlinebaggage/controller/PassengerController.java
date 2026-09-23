package com.vanshika.airlinebaggage.controller;

import com.vanshika.airlinebaggage.entity.Passenger;
import com.vanshika.airlinebaggage.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @PostMapping
    public Passenger createPassenger(@Valid @RequestBody Passenger passenger) {
        return passengerService.savePassenger(passenger);
    }

    @GetMapping
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Passenger getPassengerById(@PathVariable Long id) {
        return passengerService.getPassengerById(id);
    }

    @PutMapping("/{id}")
    public Passenger updatePassenger(
            @PathVariable Long id,
            @Valid @RequestBody Passenger passenger) {

        return passengerService.updatePassenger(id, passenger);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }
}