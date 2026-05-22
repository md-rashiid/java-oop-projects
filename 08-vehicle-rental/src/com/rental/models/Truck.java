package com.rental.models;
public class Truck extends Vehicle {
    private double loadCapacityTons;

    public Truck(String vehicleId, String brand, String model, double pricePerDay, double loadCapacityTons) {
        super(vehicleId, brand, model, pricePerDay);
        this.loadCapacityTons = loadCapacityTons;
    }

    public double getLoadCapacityTons() { return loadCapacityTons; }
    public void setLoadCapacityTons(double loadCapacityTons) { this.loadCapacityTons = loadCapacityTons; }

    @Override
    public void display() {
        System.out.println("[Truck] ID: " + getVehicleId() + " | Model: " + getModel() + 
                           " | Price/Day: " + getPricePerDay() + " | Capacity: " + loadCapacityTons + " Tons");
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }

    @Override
    public String getFuelType() {
        return "Diesel";
    }
}