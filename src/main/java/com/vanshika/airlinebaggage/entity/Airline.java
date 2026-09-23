package com.vanshika.airlinebaggage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private double allowedWeight;

    @Positive
    private double penaltyPerKg;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAllowedWeight() {
        return allowedWeight;
    }

    public void setAllowedWeight(double allowedWeight) {
        this.allowedWeight = allowedWeight;
    }

    public double getPenaltyPerKg() {
        return penaltyPerKg;
    }

    public void setPenaltyPerKg(double penaltyPerKg) {
        this.penaltyPerKg = penaltyPerKg;
    }
}
