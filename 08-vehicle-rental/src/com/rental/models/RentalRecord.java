package com.rental.models;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentalRecord {
    private String rentalId;
    private String customerId;
    private String vehicleId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private LocalDate actualReturn;
    private boolean isClosed;
    private double pricePerDay;

    // Default Constructor
    public RentalRecord() {}

    // Parameterized Constructor for creating a new booking
    public RentalRecord(String rentalId, String customerId, String vehicleId, LocalDate rentalDate, LocalDate returnDate, double pricePerDay) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.pricePerDay = pricePerDay;
        this.isClosed = false; // Initial stage par always open rahega
    }

    // Getters and Setters
    public String getRentalId() { return rentalId; }
    public void setRentalId(String rentalId) { this.rentalId = rentalId; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public LocalDate getRentalDate() { return rentalDate; }
    public void setRentalDate(LocalDate rentalDate) { this.rentalDate = rentalDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public LocalDate getActualReturn() { return actualReturn; }
    public void setActualReturn(LocalDate actualReturn) { this.actualReturn = actualReturn; }

    public boolean isClosed() { return isClosed; }
    public void setClosed(boolean closed) { isClosed = closed; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    // Business Logic Methods
    public double calculateBill() {
        long days = ChronoUnit.DAYS.between(rentalDate, returnDate);
        if (days <= 0) days = 1;
        return days * pricePerDay;
    }

    public double calculateFine() {
        if (actualReturn == null || !actualReturn.isAfter(returnDate)) {
            return 0.0;
        }
        long extraDays = ChronoUnit.DAYS.between(returnDate, actualReturn);
        return extraDays * 500.0; // Late fine 500 per day
    }
}