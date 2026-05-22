package com.rental.models;
 public class Car extends Vehicle{
    private int numberOfSeat;
    private boolean hasAC;

    public Car(String vehicleId, String brand, String model, double pricePerDay, int numberOfSeat, boolean hasAC){
        super(vehicleId,brand,model,pricePerDay);
        this.numberOfSeat=numberOfSeat;
        this.hasAC=hasAC;
    }

    @Override
    public String getVehicleType(){
        return "Car";
    }

    @Override
    public String getFuelType(){
        return "Petrol";
    }

    @Override
    public void display(){
        System.out.println("Vehicle Type: " + getVehicleType());
        System.out.println("Brand: " + getBrand());
        System.out.println("Model: " + getModel());
        System.out.println("Number of Seats: " + numberOfSeat);
        System.out.println("Has AC: " + hasAC);
        System.out.println("Price Per Day: " + getPricePerDay());
    }
}