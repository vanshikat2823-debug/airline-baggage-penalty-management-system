package com.vanshika.airlinebaggage.service;

import com.vanshika.airlinebaggage.entity.Passenger;
import com.vanshika.airlinebaggage.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger getPassengerById(Long id) {

        return passengerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
    }

    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {

        Passenger existingPassenger = passengerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        existingPassenger.setName(updatedPassenger.getName());
        existingPassenger.setPassportNumber(updatedPassenger.getPassportNumber());
        existingPassenger.setFlightNumber(updatedPassenger.getFlightNumber());
        existingPassenger.setAirline(updatedPassenger.getAirline());

        return passengerRepository.save(existingPassenger);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}