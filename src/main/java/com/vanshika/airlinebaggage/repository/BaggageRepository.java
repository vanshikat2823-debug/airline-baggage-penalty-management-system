package com.vanshika.airlinebaggage.repository;

import com.vanshika.airlinebaggage.entity.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {

    long countByStatus(String status);
}