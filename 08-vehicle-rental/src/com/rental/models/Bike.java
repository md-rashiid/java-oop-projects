package com.rental.models;
public class Bike extends Vehicle {
    // Private field (Encapsulation)
    private int engineCC;

    // Added Constructor to match Parent class
    public Bike(String vehicleId, String brand, String model, double pricePerDay, int engineCC) {
        super(vehicleId, brand, model, pricePerDay);
        this.engineCC = engineCC;
    }

    // Getter Method for engineCC
    public int getEngineCC() { 
        return engineCC; 
    }

    // Renamed from displayInfo to display to match Vehicle class
    @Override
    public void display() {
        System.out.println("[Bike] ID: " + getVehicleId() + 
                           " | Model: " + getModel() + 
                           " | Price/Day: " + getPricePerDay() + 
                           " | Engine: " + engineCC + "cc");
    }

    @Override
    public String getFuelType() {
        return "Petrol";
    }

    // Overriding parent method to identify the type as "Bike"
    @Override
    public String getVehicleType() {
        return "Bike";
    }
}