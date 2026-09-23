package com.vanshika.airlinebaggage.service;

import com.vanshika.airlinebaggage.dto.DashboardSummary;
import com.vanshika.airlinebaggage.entity.Baggage;
import com.vanshika.airlinebaggage.repository.BaggageRepository;
import com.vanshika.airlinebaggage.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final PassengerRepository passengerRepository;
    private final BaggageRepository baggageRepository;

    public DashboardService(
            PassengerRepository passengerRepository,
            BaggageRepository baggageRepository) {

        this.passengerRepository = passengerRepository;
        this.baggageRepository = baggageRepository;
    }

    public DashboardSummary getDashboardSummary() {

        long totalPassengers = passengerRepository.count();

        long totalBaggage = baggageRepository.count();

        long exceededBaggage =
                baggageRepository.countByStatus("EXCEEDED");

        long withinLimitBaggage =
                baggageRepository.countByStatus("WITHIN_LIMIT");

        List<Baggage> allBaggage =
                baggageRepository.findAll();

        double totalPenaltyAmount = allBaggage.stream()
                .mapToDouble(Baggage::getPenaltyAmount)
                .sum();

        return new DashboardSummary(
                totalPassengers,
                totalBaggage,
                exceededBaggage,
                withinLimitBaggage,
                totalPenaltyAmount
        );
    }
}
