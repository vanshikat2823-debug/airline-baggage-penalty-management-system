package com.vanshika.airlinebaggage.repository;

import com.vanshika.airlinebaggage.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository  extends JpaRepository<Airline, Long> {
}
