package com.rental.models;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

public class RentalAgency {
    private String agencyName;
    private HashMap<String, Vehicle> vehicles = new HashMap<>();
    private HashMap<String, Customer> customers = new HashMap<>();
    private ArrayList<RentalRecord> rentals = new ArrayList<>();

    // Constructor to set Agency Name directly
    public RentalAgency(String agencyName) {
        this.agencyName = agencyName;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
        System.out.println("Vehicle added to inventory: " + vehicle.getModel());
    }

    public void registerCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
        System.out.println("Customer registered successfully: " + customer.getName());
    }

    public void rentVehicle(String rentalId, String customerId, String vehicleId, LocalDate rentDate, LocalDate retDate) {
        if (!vehicles.containsKey(vehicleId)) {
            System.out.println("Error: Vehicle not found!");
            return;
        }
        if (!customers.containsKey(customerId)) {
            System.out.println("Error: Customer not registered!");
            return;
        }

        Vehicle vehicle = vehicles.get(vehicleId);
        if (!vehicle.isAvailable()) {
            System.out.println("Error: Vehicle is already out on rent!");
            return;
        }

        // Object Creation using Parameterized Constructor
        RentalRecord record = new RentalRecord(rentalId, customerId, vehicleId, rentDate, retDate, vehicle.getPricePerDay());
        rentals.add(record);
        
        vehicle.setAvailable(false); // Booked status
        System.out.println("Success: " + vehicle.getModel() + " has been rented to client.");
    }

    public void returnVehicle(String rentalId, LocalDate actualReturnDate) {
        for (RentalRecord record : rentals) {
            if (record.getRentalId().equals(rentalId) && !record.isClosed()) {
                record.setActualReturn(actualReturnDate);
                record.setClosed(true);

                Vehicle vehicle = vehicles.get(record.getVehicleId());
                vehicle.setAvailable(true); // Free up inventory

                double bill = record.calculateBill();
                double fine = record.calculateFine();

                System.out.println("\n=== INVOICE SUMMARY ===");
                System.out.println("Vehicle Model: " + vehicle.getModel());
                System.out.println("Rental Charges: Rs. " + bill);
                System.out.println("Delay Penalty: Rs. " + fine);
                System.out.println("Net Payable Amount: Rs. " + (bill + fine));
                System.out.println("=======================");
                return;
            }
        }
        System.out.println("Error: No active rental log matched with this ID.");
    }

    public void searchByType(String type) {
        System.out.println("\n--- Search Results for Type: " + type + " ---");
        for (Vehicle v : vehicles.values()) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                v.display();
            }
        }
    }

    public void displayAvailable() {
        System.out.println("\n--- Currently Available Fleet ---");
        for (Vehicle v : vehicles.values()) {
            if (v.isAvailable()) {
                v.display();
            }
        }
    }
}
