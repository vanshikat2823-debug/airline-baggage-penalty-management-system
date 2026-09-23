package com.vanshika.airlinebaggage.repository;

import com.vanshika.airlinebaggage.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
