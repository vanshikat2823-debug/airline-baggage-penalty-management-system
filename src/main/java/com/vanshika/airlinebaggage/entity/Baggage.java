package com.vanshika.airlinebaggage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String baggageTag;

    @Positive
    private double actualWeight;

    private String status;

    private double excessWeight;

    private double penaltyAmount;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaggageTag() {
        return baggageTag;
    }

    public void setBaggageTag(String baggageTag) {
        this.baggageTag = baggageTag;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getExcessWeight() {
        return excessWeight;
    }

    public void setExcessWeight(double excessWeight) {
        this.excessWeight = excessWeight;
    }

    public double getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(double penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}