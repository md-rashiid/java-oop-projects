package com.rental.models;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // One-liner Initialization with constructors!
        RentalAgency agency = new RentalAgency("Elite Wheels India");

        // 1. Adding Vehicles cleanly
        Car car1 = new Car("C001", "Mahindra", "Thar", 3000.0, 4, true);
        Bike bike1 = new Bike("B001", "KTM", "Duke 390", 1200.0, 373);
        Truck truck1 = new Truck("T001", "Tata", "Prima", 8000.0, 10.5);

        agency.addVehicle(car1);
        agency.addVehicle(bike1);
        agency.addVehicle(truck1);

        // 2. Registering Customer
        Customer client = new Customer("CUST_RASHID", "Rashid", "rashid@test.com", "9988776655", "IND-BR-09");
        agency.registerCustomer(client);

        // 3. Testing Flow
        System.out.println("\n--- Initiating Booking ---");
        LocalDate today = LocalDate.now();
        LocalDate targetReturn = today.plusDays(3); // 3 Days Booking

        agency.rentVehicle("R_LOG_01", "CUST_RASHID", "B001", today, targetReturn);

        // Checking availability
        agency.displayAvailable();

        // 4. Returning 2 days late (Total 5 days passed)
        System.out.println("\n--- Triggering Return Execution ---");
        LocalDate actualReturnDate = today.plusDays(5); 
        agency.returnVehicle("R_LOG_01", actualReturnDate);
    }
}