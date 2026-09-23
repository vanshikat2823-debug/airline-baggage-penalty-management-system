package com.vanshika.airlinebaggage.service;

import com.vanshika.airlinebaggage.entity.Baggage;
import com.vanshika.airlinebaggage.entity.Passenger;
import com.vanshika.airlinebaggage.repository.BaggageRepository;
import com.vanshika.airlinebaggage.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaggageService {

    private final BaggageRepository baggageRepository;
    private final PassengerRepository passengerRepository;

    public BaggageService(
            BaggageRepository baggageRepository,
            PassengerRepository passengerRepository) {

        this.baggageRepository = baggageRepository;
        this.passengerRepository = passengerRepository;
    }

    public Baggage saveBaggage(Baggage baggage) {

        if (baggage.getPassenger() == null) {
            throw new IllegalArgumentException("Passenger is required");
        }

        Long passengerId = baggage.getPassenger().getId();

        Passenger passenger = passengerRepository
                .findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        baggage.setPassenger(passenger);

        calculatePenalty(baggage, passenger);

        return baggageRepository.save(baggage);
    }

    public List<Baggage> getAllBaggage() {
        return baggageRepository.findAll();
    }

    public Baggage getBaggageById(Long id) {

        return baggageRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Baggage not found"));
    }

    public Baggage updateBaggage(Long id, Baggage updatedBaggage) {

        Baggage existingBaggage = baggageRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Baggage not found"));

        if (updatedBaggage.getPassenger() == null) {
            throw new IllegalArgumentException("Passenger is required");
        }

        Long passengerId = updatedBaggage.getPassenger().getId();

        Passenger passenger = passengerRepository
                .findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        existingBaggage.setBaggageTag(updatedBaggage.getBaggageTag());
        existingBaggage.setActualWeight(updatedBaggage.getActualWeight());
        existingBaggage.setPassenger(passenger);

        calculatePenalty(existingBaggage, passenger);

        return baggageRepository.save(existingBaggage);
    }

    private void calculatePenalty(Baggage baggage, Passenger passenger) {

        double allowedWeight =
                passenger.getAirline().getAllowedWeight();

        double penaltyPerKg =
                passenger.getAirline().getPenaltyPerKg();

        double actualWeight =
                baggage.getActualWeight();

        if (actualWeight > allowedWeight) {

            double excessWeight =
                    actualWeight - allowedWeight;

            double penaltyAmount =
                    excessWeight * penaltyPerKg;

            baggage.setExcessWeight(excessWeight);
            baggage.setPenaltyAmount(penaltyAmount);
            baggage.setStatus("EXCEEDED");

        } else {

            baggage.setExcessWeight(0);
            baggage.setPenaltyAmount(0);
            baggage.setStatus("WITHIN_LIMIT");
        }
    }

    public void deleteBaggage(Long id) {
        baggageRepository.deleteById(id);
    }
}