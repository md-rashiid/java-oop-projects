# 🛒 E-Commerce System — Java OOP Capstone Project

A fully functional e-commerce backend built in **pure Java** with no frameworks or libraries.
This project simulates a real online shopping platform — customers can browse products, manage
a cart, place orders with different payment methods, and admins can manage inventory and track orders.

Built as the capstone project for the **Core Java OOP Module** to apply every OOP concept
learned — Abstraction, Inheritance, Polymorphism, Encapsulation, Composition, and the Strategy
Design Pattern — all in one real-world system.

---

## ✨ Features

| Feature | What it does |
|---------|-------------|
| 🛒 Shopping Cart | Add items, remove items, update quantity, auto-calculate total |
| 💳 Multiple Payments | Pay with Credit Card, UPI, or Cash on Delivery |
| 📦 Order Tracking | Orders move through CONFIRMED → SHIPPED → DELIVERED |
| 👤 Customer | Browse products, manage cart, place orders, view order history |
| 🔐 Admin | Add/remove products, restock inventory, update order status |
| 📊 Inventory | Real stock tracking — stock reduces automatically after every order |
| ⭐ Reviews | Customers rate products 1–5 stars, average rating auto-calculated |
| 🔍 Search & Filter | Search products by name, filter by category |

---

## 🗂️ Project Structure

```
05-ecommerce-system/
│
├── Product.java            ← Abstract base class — all products extend this
├── Electronics.java        ← extends Product  (brand, warranty)
├── Clothing.java           ← extends Product  (size, color, material)
├── Book.java               ← extends Product  (author, isbn, genre)
│
├── User.java               ← Abstract base class — all users extend this
├── Customer.java           ← extends User  (cart, orderHistory, placeOrder)
├── Admin.java              ← extends User  (manage inventory, update orders)
│
├── PaymentMethod.java      ← Interface — Strategy Pattern
├── CreditCardPayment.java  ← implements PaymentMethod
├── UPIPayment.java         ← implements PaymentMethod
├── CashOnDelivery.java     ← implements PaymentMethod
│
├── Cart.java               ← Holds list of CartItems
├── CartItem.java           ← One product + its quantity inside the cart
│
├── Order.java              ← A confirmed order with status and payment info
├── OrderItem.java          ← Snapshot of product details at the time of purchase
│
├── Review.java             ← Star rating + comment on a product
├── Inventory.java          ← HashMap-based stock management system
│
└── Main.java               ← Full demo — runs all 13 steps end to end
```

---

## 🧠 OOP Concepts — Explained with This Project's Code

### 1. 🔷 Abstraction

**What it means:** Hide the internal complexity. Show only what is necessary.
You define the *what* in an abstract class, and subclasses define the *how*.

**How it's used here:**

`Product.java` is an abstract class. It holds the common fields every product must have
(productId, name, price, description, reviews). But two methods are left abstract:

```java
public abstract String getCategory();
public abstract void displayDetails();
```

This means: every product *must* have a category and must be able to display itself —
but HOW they display is their own business. A `Book` shows author and ISBN,
an `Electronics` shows brand and warranty. The abstract class forces this contract.

Same for `User.java` — it is abstract with:
```java
public abstract String getRole();
public abstract void displayDashboard();
```

A `Customer` dashboard shows cart and order count.
An `Admin` dashboard shows admin level.
The base class says "you must have a dashboard" — the subclass decides what's in it.

---

### 2. 🔷 Inheritance

**What it means:** A child class gets all the fields and methods of its parent class.
It can also add its own fields and override parent behavior. This is the IS-A relationship.

**How it's used here:**

**Product hierarchy:**
```
Product (abstract)
  ├── Electronics   IS-A Product  →  adds: brand, warrantyMonths
  ├── Clothing      IS-A Product  →  adds: size, color, material
  └── Book          IS-A Product  →  adds: author, isbn, genre
```

`Electronics` gets `productId`, `name`, `price`, `description`, `reviews` for free from `Product`.
It only adds what makes an electronic product unique — `brand` and `warrantyMonths`.

**User hierarchy:**
```
User (abstract)
  ├── Customer   IS-A User  →  adds: cart, orderHistory, address, placeOrder()
  └── Admin      IS-A User  →  adds: adminLevel, addProduct(), updateStock()
```

Both `Customer` and `Admin` inherit `userId`, `name`, `email`, `phone` from `User`.
This avoids writing these fields twice. DRY — Don't Repeat Yourself.

---

### 3. 🔷 Polymorphism

**What it means:** One interface, many forms. The same method call behaves
differently depending on which object is behind it. Decided at runtime.

**How it's used here:**

`PaymentMethod` is an interface with one key method:
```java
boolean processPayment(double amount);
```

Three classes implement this interface — `CreditCardPayment`, `UPIPayment`, `CashOnDelivery`.

In `Customer.placeOrder()`:
```java
boolean paid = paymentMethod.processPayment(total);
```

The same line of code works for ALL three payment types. Java decides at runtime
which `processPayment()` to call based on the actual object passed in.

In `Main.java`:
```java
PaymentMethod creditCard = new CreditCardPayment("4111...", "Aman Singh", "123");
PaymentMethod upi        = new UPIPayment("priya@upi");
PaymentMethod cod        = new CashOnDelivery();
```

Three different objects, same type `PaymentMethod`. This is runtime polymorphism.

---

### 4. 🔷 Encapsulation

**What it means:** Keep data private. Control who can read or change it.
Private fields + public getters/setters = encapsulation.

**How it's used here:**

Every single field in every class is `private`. Example from `Product.java`:
```java
private String productId;
private String name;
private double price;
private String description;
private List<Review> reviews;
```

Nobody outside `Product` can do `product.price = -500`. That would be dangerous.
Instead, they use `product.getPrice()` to read, and the class controls how price is set.

In `Review.java`, the constructor validates the rating:
```java
if (rating < 1 || rating > 5)
    throw new IllegalArgumentException("Rating must be between 1 and 5");
```

This is the power of encapsulation — bad data is blocked at the door.

---

### 5. 🔷 Composition

**What it means:** Build complex objects by combining simpler objects together.
This is the HAS-A relationship. Instead of inheriting, you include.

**How it's used here:**

```
Customer  HAS-A  Cart
Cart      HAS-A  List<CartItem>
CartItem  HAS-A  Product

Customer  HAS-A  List<Order>
Order     HAS-A  List<OrderItem>

Inventory HAS-A  HashMap<String, Product>  (the product catalog)
Inventory HAS-A  HashMap<String, Integer>  (the stock levels)
```

A `Cart` is not a `Product` — it *contains* products. That's composition.
An `Order` is not a `User` — it *belongs to* a user and *contains* order items.

**Important design choice — `OrderItem` vs `CartItem`:**
When an order is placed, we don't store the `Product` object directly in the order.
We create an `OrderItem` that saves the product's name and price *at that moment*.

Why? Because if the admin later changes the laptop's price from ₹65,000 to ₹70,000,
your old order should still show ₹65,000 — the price you actually paid. This is a
snapshot pattern using composition.

---

### 6. 🔷 Strategy Pattern (Design Pattern)

**What it means:** Define a family of behaviors, put each in its own class,
and make them interchangeable. The client doesn't care which one it uses.

**How it's used here:**

Without Strategy Pattern, payment would look like this (bad code):
```java
if (paymentType.equals("CreditCard")) {
    // credit card logic
} else if (paymentType.equals("UPI")) {
    // upi logic
} else if (paymentType.equals("COD")) {
    // cod logic
}
```

Every new payment method means editing this if-else. That's fragile.

With Strategy Pattern:
```java
// PaymentMethod.java — the strategy interface
public interface PaymentMethod {
    boolean processPayment(double amount);
    String getPaymentType();
    String getTransactionId();
}
```

Each payment type is its own class. `Customer.placeOrder()` only knows about
`PaymentMethod` — it doesn't care what kind. You can add `NetBankingPayment`
or `WalletPayment` tomorrow without touching a single existing class.
This follows the **Open/Closed Principle** — open for extension, closed for modification.

---

## 🔗 Full Class Relationship Diagram

```
                    ┌─────────────────────┐
                    │   Product (abstract) │
                    └──────────┬──────────┘
               ┌───────────────┼───────────────┐
               ▼               ▼               ▼
         Electronics        Clothing          Book


                    ┌─────────────────────┐
                    │    User (abstract)   │
                    └──────────┬──────────┘
                    ┌──────────┴──────────┐
                    ▼                     ▼
                Customer               Admin
                  │                      │
           has-a: Cart            manages: Inventory
           has-a: List<Order>     updates: Order status


         Cart ──has──▶ List<CartItem> ──has──▶ Product
         Order ──has──▶ List<OrderItem>  (price snapshot)


              ┌─────────────────────────┐
              │  PaymentMethod (interface)│
              └────────────┬────────────┘
         ┌─────────────────┼─────────────────┐
         ▼                 ▼                 ▼
  CreditCardPayment    UPIPayment     CashOnDelivery
```

---

## 🚀 How to Run

```bash
# Navigate to the project folder
cd "Projects/01-Core-Java/18-Mini-Projects/src/05-ecommerce-system"

# Compile all Java files at once
javac *.java

# Run the program
java Main
```

**Requirement:** Java 8 or above

---

## 📋 What the Demo Does (Main.java — 13 Steps)

| Step | Action | OOP Concept Shown |
|------|---------|-------------------|
| 1 | Admin `Ravi Kumar` is created | Inheritance, Abstraction |
| 2 | Admin adds 7 products to inventory | Polymorphism (Product types), Composition |
| 3 | Customers `Aman` and `Priya` register | Inheritance, Encapsulation |
| 4 | Search by name, filter by category | Inventory HashMap logic |
| 5 | Add reviews, calculate average rating | Encapsulation, Composition |
| 6 | Aman adds items to cart, updates quantity | Composition (Cart + CartItem) |
| 7 | Aman pays with **Credit Card** | Strategy Pattern, Polymorphism |
| 8 | Priya fills her cart | Composition |
| 9 | Priya pays with **UPI** | Strategy Pattern, Polymorphism |
| 10 | Priya pays with **Cash on Delivery** | Strategy Pattern, Polymorphism |
| 11 | Admin views all orders, marks SHIPPED/DELIVERED | Encapsulation, Admin role |
| 12 | Both customers view order history | Composition (Customer has Orders) |
| 13 | Admin restocks Dell Laptop | Inventory management |

---

## 💡 Key Design Decisions Explained

**Why two separate classes `CartItem` and `OrderItem`?**
`CartItem` is temporary — it lives in the cart until checkout.
`OrderItem` is permanent — it stores the price *at the time of purchase* so that
future price changes don't affect old orders. This is called a price snapshot.

**Why is `PaymentMethod` an interface and not an abstract class?**
An interface enforces a contract without forcing inheritance.
`CreditCardPayment` doesn't need to inherit any shared state — it just needs
to guarantee it can `processPayment()`. Interface is the right tool here.

**Why does `Inventory` use two HashMaps?**
One HashMap stores `productId → Product object` (the product details).
Another stores `productId → Integer` (the stock count).
Separating them keeps the `Product` class clean — a product doesn't need to
know how many units are in stock. That's the inventory's job.

**Why does `Order` save `customerName` and `deliveryAddress` as Strings?**
Because if a customer later changes their name or address, old orders should
still show the original delivery details. Same reason as `OrderItem` pricing.

---

## 🛣️ What Can Be Added Next

- **File I/O** — Save orders to a `.txt` or `.csv` file using Java File I/O
- **Exception Handling** — Custom exceptions like `OutOfStockException`, `PaymentFailedException`
- **Generics** — Make `Cart<T extends Product>` type-safe
- **Collections** — Sort orders by date, sort products by price using `Comparator`
- **Spring Boot** — Convert this into a REST API with endpoints like `POST /orders`, `GET /products`
- **Database** — Connect to MySQL with JDBC or Hibernate

---

## 👨‍💻 Author

**Md. Rashid**
- 📚 Learning Path: Core Java → Java 8 → Spring Boot → Microservices
- 🗓️ Completed: May 2026

---

> *Built as part of the **Backend Engineering Path** — Core Java OOP Module*
> *This project uses zero external libraries — pure Java only.*
