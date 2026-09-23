package com.vanshika.airlinebaggage.dto;

public class DashboardSummary {

    private long totalPassengers;
    private long totalBaggage;
    private long exceededBaggage;
    private long withinLimitBaggage;
    private double totalPenaltyAmount;

    public DashboardSummary() {
    }

    public DashboardSummary(
            long totalPassengers,
            long totalBaggage,
            long exceededBaggage,
            long withinLimitBaggage,
            double totalPenaltyAmount) {

        this.totalPassengers = totalPassengers;
        this.totalBaggage = totalBaggage;
        this.exceededBaggage = exceededBaggage;
        this.withinLimitBaggage = withinLimitBaggage;
        this.totalPenaltyAmount = totalPenaltyAmount;
    }

    public long getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(long totalPassengers) {
        this.totalPassengers = totalPassengers;
    }

    public long getTotalBaggage() {
        return totalBaggage;
    }

    public void setTotalBaggage(long totalBaggage) {
        this.totalBaggage = totalBaggage;
    }

    public long getExceededBaggage() {
        return exceededBaggage;
    }

    public void setExceededBaggage(long exceededBaggage) {
        this.exceededBaggage = exceededBaggage;
    }

    public long getWithinLimitBaggage() {
        return withinLimitBaggage;
    }

    public void setWithinLimitBaggage(long withinLimitBaggage) {
        this.withinLimitBaggage = withinLimitBaggage;
    }

    public double getTotalPenaltyAmount() {
        return totalPenaltyAmount;
    }

    public void setTotalPenaltyAmount(double totalPenaltyAmount) {
        this.totalPenaltyAmount = totalPenaltyAmount;
    }
}