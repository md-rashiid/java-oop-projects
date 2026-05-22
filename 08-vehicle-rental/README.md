# 🚗 Vehicle Rental System — Java OOP Project

A complete vehicle rental management system built in **pure Java** that simulates a real-world rental agency.
Customers can rent different types of vehicles (Cars, Bikes, Trucks), the system tracks rental records,
calculates rental charges, and applies late return penalties. This project demonstrates all core OOP
concepts through a practical rental business scenario.

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🚙 Multi-vehicle Support | Manage Cars, Bikes, and Trucks with different specifications |
| 👤 Customer Registration | Register customers with license verification |
| 🔑 Vehicle Booking | Rent any available vehicle for specified dates |
| 📅 Date Tracking | Track rental dates and actual return dates using `LocalDate` |
| 💰 Smart Billing | Calculate rental charges based on rental duration |
| ⏱️ Late Penalties | Automatic fine calculation for late returns (₹500 per extra day) |
| 🔍 Search & Filter | Search vehicles by type (Car/Bike/Truck) |
| 📊 Inventory Management | Real-time availability tracking |
| 📋 Rental History | Complete rental records with invoice generation |

---

## 🗂️ Project Structure

```
08-vehicle-rental/
└── src/com/rental/models/
    ├── Vehicle.java         ← Abstract base class for all vehicles
    ├── Car.java             ← extends Vehicle (seats, AC features)
    ├── Bike.java            ← extends Vehicle (engine CC)
    ├── Truck.java           ← extends Vehicle (load capacity)
    ├── Customer.java        ← Customer profile with license
    ├── RentalRecord.java    ← Booking record + billing logic
    ├── RentalAgency.java    ← Core system (inventory, bookings)
    └── Main.java            ← Full demo (6 steps)
```

---

## 🧠 OOP Concepts — Detailed Explanation

### 1. 🔷 Abstraction

**What it means:** Define a blueprint with abstract methods that subclasses MUST implement.
Hide implementation details, show only essential behavior.

**How it's used:**

`Vehicle.java` is an abstract class with 3 abstract methods:

```java
public abstract class Vehicle {
    private String vehicleId, brand, model;
    private double pricePerDay;
    private boolean isAvailable;
    
    public abstract String getVehicleType();
    public abstract String getFuelType();
    public abstract void display();
}
```

Why this is smart:
- Every vehicle MUST define its type (Car/Bike/Truck)
- Every vehicle MUST declare fuel type
- Every vehicle MUST have its own display format

A Car displays: `"Vehicle Type: Car | Seats: 4 | AC: Yes | Price: ₹3000/day"`
A Bike displays: `"[Bike] ID: B001 | Model: Duke 390 | Price: ₹1200/day | Engine: 373cc"`
A Truck displays: `"[Truck] ID: T001 | Model: Prima | Price: ₹8000/day | Capacity: 10.5 Tons"`

Same abstract method `display()`, completely different implementation. This is abstraction.

---

### 2. 🔷 Inheritance

**What it means:** Child classes inherit all fields and methods from the parent class.
They can add their own fields and override parent methods.

**How it's used:**

**Vehicle Hierarchy:**
```
Vehicle (abstract)
  ├── Car          IS-A Vehicle  →  adds: numberOfSeat, hasAC
  ├── Bike         IS-A Vehicle  →  adds: engineCC
  └── Truck        IS-A Vehicle  →  adds: loadCapacityTons
```

**Car.java:**
```java
public class Car extends Vehicle {
    private int numberOfSeat;
    private boolean hasAC;
    
    public Car(String vehicleId, String brand, String model, 
               double pricePerDay, int numberOfSeat, boolean hasAC) {
        super(vehicleId, brand, model, pricePerDay);  // Call parent constructor
        this.numberOfSeat = numberOfSeat;
        this.hasAC = hasAC;
    }
    
    @Override
    public String getVehicleType() { return "Car"; }
    
    @Override
    public String getFuelType() { return "Petrol"; }
    
    @Override
    public void display() {
        System.out.println("Vehicle Type: " + getVehicleType());
        System.out.println("Number of Seats: " + numberOfSeat);
        System.out.println("Has AC: " + hasAC);
    }
}
```

**Bike.java:**
```java
public class Bike extends Vehicle {
    private int engineCC;  // Only bikes need this
    
    public Bike(String vehicleId, String brand, String model, 
                double pricePerDay, int engineCC) {
        super(vehicleId, brand, model, pricePerDay);
        this.engineCC = engineCC;
    }
    
    @Override
    public String getVehicleType() { return "Bike"; }
    
    @Override
    public void display() {
        System.out.println("[Bike] ID: " + getVehicleId() + 
                         " | Engine: " + engineCC + "cc");
    }
}
```

**Key benefit:** No code duplication. All common vehicle data (vehicleId, brand, model, price)
is written once in `Vehicle`, reused by all three subclasses.

---

### 3. 🔷 Polymorphism

**What it means:** Same method name, different behavior based on the actual object type.
Decided at runtime.

**How it's used:**

In `RentalAgency.displayAvailable()`:

```java
public void displayAvailable() {
    System.out.println("--- Currently Available Fleet ---");
    for (Vehicle v : vehicles.values()) {  // v is just a Vehicle reference
        if (v.isAvailable()) {
            v.display();  // Which display() runs? Depends on actual object type!
        }
    }
}
```

At runtime:
- If `v` actually holds a `Car` object → `Car.display()` runs
- If `v` actually holds a `Bike` object → `Bike.display()` runs
- If `v` actually holds a `Truck` object → `Truck.display()` runs

Same method call, three different outputs. This is **runtime polymorphism**.

---

### 4. 🔷 Encapsulation

**What it means:** Keep data private. Control how data is accessed through public getters/setters.
Protect data from invalid modifications.

**How it's used:**

**Vehicle.java:**
```java
public class Vehicle {
    private String vehicleId;      // PRIVATE - nobody can change directly
    private double pricePerDay;
    private boolean isAvailable;
    
    public String getVehicleId() { return vehicleId; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return isAvailable; }
    
    public void setAvailable(boolean available) {  // Controlled access
        this.isAvailable = available;
    }
}
```

Nobody can do `vehicle.pricePerDay = -500` (which would be wrong).
They can only read via `getPricePerDay()`.

**RentalRecord.java:**
```java
public double calculateFine() {
    if (actualReturn == null || !actualReturn.isAfter(returnDate)) {
        return 0.0;  // No fine if returned on time
    }
    long extraDays = ChronoUnit.DAYS.between(returnDate, actualReturn);
    return extraDays * 500.0;  // ₹500 per extra day
}
```

The fine calculation logic is encapsulated inside `RentalRecord`.
From outside, you just call `calculateFine()` — you don't need to know HOW it calculates.

---

### 5. 🔷 Composition

**What it means:** Build complex objects by combining simpler objects.
HAS-A relationship — an object contains other objects.

**How it's used:**

```
RentalAgency  HAS-A  HashMap<String, Vehicle>  (vehicle inventory)
RentalAgency  HAS-A  HashMap<String, Customer> (registered customers)
RentalAgency  HAS-A  ArrayList<RentalRecord>   (all bookings)

RentalRecord  HAS-A  customerId (string reference, not Customer object)
RentalRecord  HAS-A  vehicleId  (string reference, not Vehicle object)
```

**RentalAgency.java:**
```java
public class RentalAgency {
    private HashMap<String, Vehicle> vehicles;    // Inventory
    private HashMap<String, Customer> customers;  // Customer database
    private ArrayList<RentalRecord> rentals;      // Booking history
    
    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getVehicleId(), vehicle);
    }
    
    public void registerCustomer(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }
    
    public void rentVehicle(String rentalId, String customerId, String vehicleId, ...) {
        RentalRecord record = new RentalRecord(rentalId, customerId, vehicleId, ...);
        rentals.add(record);
    }
}
```

A `RentalAgency` is not a `Vehicle` and not a `Customer`.
It *owns* and *manages* collections of them. That's composition.

**Why this design matters:**
- A rental can exist even if the customer details change later (we saved customerId, not the Customer object)
- A vehicle can be re-rented after return
- Rental history is preserved separately

---

### 6. 🔷 Date & Time Handling (Bonus OOP)

**How it's used:**

`RentalRecord.java` uses `java.time.LocalDate` to calculate rental duration:

```java
public double calculateBill() {
    long days = ChronoUnit.DAYS.between(rentalDate, returnDate);
    if (days <= 0) days = 1;  // At least 1 day charge
    return days * pricePerDay;
}

public double calculateFine() {
    if (actualReturn == null || !actualReturn.isAfter(returnDate)) {
        return 0.0;  // No fine if on-time return
    }
    long extraDays = ChronoUnit.DAYS.between(returnDate, actualReturn);
    return extraDays * 500.0;  // ₹500 per late day
}
```

**Real example from demo:**
```
Rental Date: 2026-05-22 (today)
Return Date: 2026-05-25 (3 days later)
Actual Return: 2026-05-27 (2 days late)

Bill = 3 days × ₹1200/day = ₹3600
Fine = 2 extra days × ₹500 = ₹1000
Total = ₹4600
```

---

## 🔗 Complete Class Relationship Diagram

```
                Vehicle (abstract)
                  ▲
        ┌─────────┼─────────┐
        ▼         ▼         ▼
       Car      Bike      Truck
     (4 seats) (373cc)  (10.5 tons)


    Customer
  (name, email,
   phone, license)


  RentalRecord
  (rentalId, customerId, vehicleId,
   rentalDate, returnDate, actualReturn,
   pricePerDay, isClosed)
   └─ calculateBill()
   └─ calculateFine()


  RentalAgency (Core System)
    ├─ HashMap<String, Vehicle>    (inventory)
    ├─ HashMap<String, Customer>   (customers)
    └─ ArrayList<RentalRecord>     (bookings)
      ├─ addVehicle()
      ├─ registerCustomer()
      ├─ rentVehicle()
      ├─ returnVehicle()
      ├─ displayAvailable()
      └─ searchByType()
```

---

## 🚀 How to Run

```bash
# Navigate to the project
cd "Projects/01-Core-Java/18-Mini-Projects/src/08-vehicle-rental/src"

# Compile all classes
javac com/rental/models/*.java

# Run the demo
java com.rental.models.Main
```

**Requirement:** Java 8+

---

## 📋 Demo Flow (Main.java — 6 Steps)

| Step | Action | OOP Concept |
|------|--------|-------------|
| 1 | Create rental agency `Elite Wheels India` | Encapsulation |
| 2 | Add 3 vehicles (Car, Bike, Truck) | Inheritance, Polymorphism |
| 3 | Register customer with license | Encapsulation |
| 4 | Rent a Bike for 3 days | Composition (RentalRecord creation) |
| 5 | Display available vehicles (filter updated) | Polymorphism (display() calls) |
| 6 | Return vehicle 2 days late → auto-calculate bill + fine | Date logic, Encapsulation |

---

## 💡 Demo Output Walkthrough

```
Vehicle added to inventory: Thar            ← Car added
Vehicle added to inventory: Duke 390        ← Bike added
Vehicle added to inventory: Prima           ← Truck added
Customer registered successfully: Rashid    ← Customer registered

--- Initiating Booking ---
Success: Duke 390 has been rented to client.  ← Bike is now unavailable

--- Currently Available Fleet ---
Vehicle Type: Car
Brand: Mahindra
Model: Thar
Number of Seats: 4
Has AC: true
Price Per Day: 3000.0                        ← Car still available
[Truck] ID: T001 | Model: Prima | ...       ← Truck still available
                                              (Bike is missing — rented out!)

--- Triggering Return Execution ---

=== INVOICE SUMMARY ===
Vehicle Model: Duke 390
Rental Charges: Rs. 3600.0                   ← 3 days × ₹1200
Delay Penalty: Rs. 1000.0                    ← 2 extra days × ₹500
Net Payable Amount: Rs. 4600.0               ← Total to pay
=======================
```

---

## 🎯 Key Design Decisions

**Why store `customerId` and `vehicleId` as strings in RentalRecord, not object references?**
- If we stored the Customer object directly, changes to customer details would affect past rentals
- If we stored the Vehicle object, we'd lose the original price if the vehicle price changes later
- String IDs are like **snapshots** — they preserve the rental record exactly as it was

**Why use HashMap for vehicles and customers, ArrayList for rentals?**
- HashMap: Fast lookup by ID (get vehicle in O(1) time)
- ArrayList: Maintain order of rentals, easy to iterate for history

**Why LocalDate instead of a custom date calculation?**
- Java's `java.time` package handles leap years, timezone issues automatically
- `ChronoUnit.DAYS.between()` correctly calculates exact day difference
- Professional, tested, industry-standard

**Why abstract Vehicle class instead of just Car, Bike, Truck?**
- Avoids code duplication (vehicleId, brand, model, pricePerDay written once)
- Easy to add new vehicle types later (Bus, SUV, etc.)
- Enforces contract — every vehicle type MUST implement `getVehicleType()` and `display()`

---

## 🛣️ What Can Be Added Next

- **File I/O** — Save rental history to a `.csv` or `.txt` file
- **Database** — Connect to MySQL with JDBC/Hibernate for persistent storage
- **Exception Handling** — Custom exceptions like `VehicleNotAvailableException`, `CustomerNotFoundException`
- **Collections** — Sort rentals by date, filter by vehicle type using `Comparator`
- **Spring Boot** — Convert to REST API with endpoints like `POST /rentals`, `GET /vehicles`
- **GUI** — JavaFX or Swing interface for better UX
- **Advanced Pricing** — Different rates for weekend vs weekday, insurance options
- **Payment Integration** — Multiple payment methods (UPI, Card, etc.)

---

## 👨‍💻 Author

**Md. Rashid**
- 📚 Learning Path: Core Java → Collections → File I/O → Spring Boot → REST APIs
- 🗓️ Completed: May 2026
- 💼 Interview Project: Demonstrates Inheritance, Polymorphism, Encapsulation, Composition

---

## 📌 Interview Tips

**Questions you might face about this project:**

1. **"Why is Vehicle abstract?"**
   - Answer: "It's a blueprint that forces all vehicle types to implement essential methods like `getVehicleType()` and `display()`. This ensures consistency without code duplication."

2. **"How would you add a new vehicle type (Bus)?"**
   - Answer: "Create `Bus extends Vehicle`, override the 3 abstract methods, add Bus-specific fields like `numberOfDoors`. No changes needed to existing code."

3. **"What if price changes after booking?"**
   - Answer: "RentalRecord saves `pricePerDay` at booking time (snapshot pattern). If agency changes price later, old bookings still show the original price."

4. **"How is inventory managed?"**
   - Answer: "When a vehicle is rented, `setAvailable(false)`. When returned, `setAvailable(true)`. HashMap stores all vehicles for O(1) lookup."

5. **"How do you calculate late fine?"**
   - Answer: "Capture `actualReturnDate`. Use `ChronoUnit.DAYS.between(returnDate, actualReturnDate)` to get extra days, multiply by ₹500."

---

> *Built as part of the **Backend Engineering Path** — Core Java OOP Module*
> *This project uses zero external libraries — pure Java Collections Framework only.*
