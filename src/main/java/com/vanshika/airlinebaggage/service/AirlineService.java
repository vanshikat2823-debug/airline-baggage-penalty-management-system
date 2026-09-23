package com.vanshika.airlinebaggage.service;

import com.vanshika.airlinebaggage.entity.Airline;
import com.vanshika.airlinebaggage.repository.AirlineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Airline getAirlineById(Long id) {

        return airlineRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found"));
    }

    public Airline updateAirline(Long id, Airline updatedAirline) {

        Airline existingAirline = airlineRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found"));

        existingAirline.setName(updatedAirline.getName());
        existingAirline.setAllowedWeight(updatedAirline.getAllowedWeight());
        existingAirline.setPenaltyPerKg(updatedAirline.getPenaltyPerKg());

        return airlineRepository.save(existingAirline);
    }

    public void deleteAirline(Long id) {
        airlineRepository.deleteById(id);
    }
}
