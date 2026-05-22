# 🏦 Advanced Banking Management System

![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java)
![Status](https://img.shields.io/badge/Status-In%20Progress-yellow?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)
![OOP](https://img.shields.io/badge/OOP-Advanced-blue?style=for-the-badge)

> A fully functional console-based Banking Management System built in **pure Java 17+**.
> Simulates real-world banking — multiple account types, secure PIN authentication,
> fund transfers, loan management, EMI calculation, and persistent File I/O storage.

---

## 📋 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [OOP Concepts](#-oop-concepts-with-code)
- [Design Patterns](#-design-patterns)
- [Key Algorithms](#-key-algorithms)
- [Console Interface](#-console-interface)
- [Installation & Setup](#-installation--setup)
- [Learning Outcomes](#-learning-outcomes)
- [Future Enhancements](#-future-enhancements)
- [Known Limitations](#-known-limitations)
- [Author](#-author)

---

## 🎯 Overview

This project is a **capstone-level Java application** that goes beyond basic OOP —
it incorporates real banking logic, design patterns, custom exceptions, and CSV-based
file persistence. Built over 2-3 weeks to demonstrate production-level thinking in Java.

### ✨ Key Highlights

| Highlight | Details |
|-----------|---------|
| 🔐 Security | PIN authentication + account locking after 3 failed attempts |
| 💰 Account Types | Savings, Current, Fixed Deposit — each with unique rules |
| 📊 Transactions | Full history, mini statement, fund transfer with atomicity |
| 🏛️ Loans | Personal, Home, Car loans with EMI breakdown |
| 💾 Persistence | All data saved to CSV files — survives app restart |
| 🎨 Design Patterns | Singleton, Factory, Strategy — industry-standard patterns |

---

## ✅ Features

### Account Management
- [x] Create Savings, Current, and Fixed Deposit accounts
- [x] Secure 4-digit PIN authentication
- [x] Account locking after 3 failed PIN attempts
- [x] View account details and balance

### Transactions
- [x] Deposit with validation and limits
- [x] Withdraw with balance check and daily limit
- [x] Fund transfer between accounts (atomic — all or nothing)
- [x] Transaction history (last 10 transactions)
- [x] Mini statement (monthly filter)

### Loan Management
- [x] Apply for Personal, Home, and Car loans
- [x] EMI calculator with monthly breakdown
- [x] Admin approval workflow
- [x] Automated interest calculation

### Admin Panel
- [x] View and manage all accounts
- [x] Approve or reject loan applications
- [x] Generate reports

### Beneficiary
- [x] Add and manage beneficiaries
- [x] Quick transfer to saved beneficiaries

---

## 🛠️ Tech Stack

| Technology | Usage |
|-----------|-------|
| Java 17+ | Core language |
| ArrayList | Transaction history, loan list |
| HashMap | Account lookup by account number |
| TreeMap | Sorted transaction records by date |
| File I/O (CSV) | Persistent data storage |
| Custom Exceptions | Domain-specific error handling |
| Java Date/Time API | Transaction timestamps, EMI dates |

---

## 🗂️ Project Structure

```
08-banking-system/
│
src/
├── models/
│   ├── Account.java            ← Abstract base class
│   ├── SavingsAccount.java     ← extends Account
│   ├── CurrentAccount.java     ← extends Account
│   ├── FDAccount.java          ← extends Account (Fixed Deposit)
│   ├── User.java               ← Customer info + PIN
│   ├── Transaction.java        ← One transaction record
│   └── Loan.java               ← Loan details + EMI
│
├── services/
│   ├── AccountService.java     ← Create, find, lock accounts
│   ├── TransactionService.java ← Deposit, withdraw, transfer
│   ├── LoanService.java        ← Apply, approve, EMI calculation
│   └── InterestCalculator.java ← Strategy pattern implementation
│
├── utils/
│   ├── FileHandler.java        ← CSV read/write
│   ├── Validator.java          ← PIN, amount, account validation
│   └── DateUtil.java           ← Date formatting helpers
│
├── exceptions/
│   ├── InsufficientBalanceException.java
│   ├── AccountLockedException.java
│   ├── InvalidPINException.java
│   └── LoanNotFoundException.java
│
└── Main.java                   ← Entry point + menu system
```

---

## 🧠 OOP Concepts with Code

### 1. Abstract Class — `Account.java`

```java
public abstract class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private boolean isLocked;
    private int failedAttempts;

    // Common behavior — all accounts share this
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Invalid amount");
        this.balance += amount;
    }

    // Abstract — each account type has different withdraw rules
    public abstract void withdraw(double amount) throws InsufficientBalanceException;
    public abstract double getInterestRate();
    public abstract String getAccountType();
}
```

---

### 2. Inheritance — Account Hierarchy

```java
// SavingsAccount — max 6 withdrawals/month, 4% interest
public class SavingsAccount extends Account {
    private int monthlyWithdrawals;

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (monthlyWithdrawals >= 6)
            throw new RuntimeException("Monthly withdrawal limit reached");
        if (getBalance() < amount)
            throw new InsufficientBalanceException("Insufficient balance");
        setBalance(getBalance() - amount);
        monthlyWithdrawals++;
    }

    @Override
    public double getInterestRate() { return 4.0; }

    @Override
    public String getAccountType() { return "Savings Account"; }
}

// CurrentAccount — unlimited withdrawals, overdraft allowed up to Rs.10,000
public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 10000;

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (getBalance() + OVERDRAFT_LIMIT < amount)
            throw new InsufficientBalanceException("Exceeds overdraft limit");
        setBalance(getBalance() - amount);
    }

    @Override
    public double getInterestRate() { return 0.0; } // No interest on current

    @Override
    public String getAccountType() { return "Current Account"; }
}
```

---

### 3. Polymorphism — Same call, different behavior

```java
// All stored as Account type — Polymorphism!
List<Account> accounts = new ArrayList<>();
accounts.add(new SavingsAccount("SA001", "Rashid", 10000));
accounts.add(new CurrentAccount("CA001", "Priya",  50000));
accounts.add(new FDAccount("FD001", "Rohit",  100000, 12));

// Same method call — different logic runs for each!
for (Account acc : accounts) {
    acc.withdraw(5000); // SavingsAccount checks monthly limit
                        // CurrentAccount checks overdraft
                        // FDAccount checks lock-in period
}
```

---

### 4. Encapsulation — Controlled access

```java
public class Account {
    private double balance;      // Nobody can touch this directly
    private String pin;          // PIN never exposed
    private int failedAttempts;  // Controlled internally

    // Balance readable but not settable from outside
    public double getBalance() { return balance; }

    // PIN verified but never returned
    public boolean verifyPIN(String inputPin) {
        if (isLocked()) throw new AccountLockedException("Account is locked");
        if (!this.pin.equals(inputPin)) {
            failedAttempts++;
            if (failedAttempts >= 3) lockAccount();
            throw new InvalidPINException("Wrong PIN. Attempts: " + failedAttempts);
        }
        failedAttempts = 0;
        return true;
    }
}
```

---

### 5. Custom Exceptions

```java
// Domain-specific exceptions — much better than generic RuntimeException
public class InsufficientBalanceException extends Exception {
    private double shortfall;

    public InsufficientBalanceException(String message, double shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public double getShortfall() { return shortfall; }
}

// Usage
try {
    account.withdraw(50000);
} catch (InsufficientBalanceException e) {
    System.out.println(e.getMessage());
    System.out.println("You need Rs." + e.getShortfall() + " more.");
} catch (AccountLockedException e) {
    System.out.println("Account locked! Contact bank.");
}
```

---

## 🎨 Design Patterns

### Singleton — `BankingSystem.java`

```java
// Only ONE instance of the bank should exist
public class BankingSystem {
    private static BankingSystem instance;

    private BankingSystem() {}  // private constructor

    public static BankingSystem getInstance() {
        if (instance == null) {
            instance = new BankingSystem();
        }
        return instance;
    }
}
// Usage
BankingSystem bank = BankingSystem.getInstance();
```

### Factory — `AccountFactory.java`

```java
// Create accounts without knowing exact class
public class AccountFactory {
    public static Account createAccount(String type, String id, String name, double balance) {
        switch (type.toUpperCase()) {
            case "SAVINGS" : return new SavingsAccount(id, name, balance);
            case "CURRENT" : return new CurrentAccount(id, name, balance);
            case "FD"      : return new FDAccount(id, name, balance, 12);
            default: throw new IllegalArgumentException("Unknown account type: " + type);
        }
    }
}
// Usage
Account acc = AccountFactory.createAccount("SAVINGS", "SA001", "Rashid", 10000);
```

### Strategy — `InterestCalculator.java`

```java
public interface InterestCalculator {
    double calculate(double principal, int months);
}

public class SavingsInterestCalculator implements InterestCalculator {
    public double calculate(double principal, int months) {
        return principal * 0.04 * months / 12; // 4% per annum
    }
}

public class FDInterestCalculator implements InterestCalculator {
    public double calculate(double principal, int months) {
        return principal * 0.07 * months / 12; // 7% per annum
    }
}
```

---

## 📐 Key Algorithms

### EMI Calculation

```
Formula: EMI = P × r × (1+r)^n / ((1+r)^n - 1)

Where:
  P = Principal loan amount
  r = Monthly interest rate (annual rate / 12 / 100)
  n = Loan tenure in months

Example:
  Loan    = Rs. 5,00,000
  Rate    = 10% per annum → r = 10/12/100 = 0.00833
  Tenure  = 60 months (5 years)
  EMI     = 5,00,000 × 0.00833 × (1.00833)^60 / ((1.00833)^60 - 1)
  EMI     = Rs. 10,624/month
  Total   = Rs. 6,37,440
  Interest= Rs. 1,37,440
```

```java
public double calculateEMI(double principal, double annualRate, int months) {
    double r = annualRate / 12 / 100;
    double emi = principal * r * Math.pow(1 + r, months)
                 / (Math.pow(1 + r, months) - 1);
    return Math.round(emi * 100.0) / 100.0;
}
```

### Fund Transfer — Atomicity

```java
// All-or-nothing: if debit succeeds but credit fails, debit is reversed
public void transfer(Account from, Account to, double amount) {
    try {
        from.withdraw(amount);  // Step 1: debit
        try {
            to.deposit(amount); // Step 2: credit
        } catch (Exception e) {
            from.deposit(amount); // ROLLBACK: reverse debit
            throw new RuntimeException("Transfer failed. Amount reversed.");
        }
    } catch (InsufficientBalanceException e) {
        throw new RuntimeException("Insufficient balance for transfer.");
    }
}
```

---

## 💻 Console Interface

```
╔══════════════════════════════════════════════╗
║         ADVANCED BANKING SYSTEM              ║
║              Welcome Back!                   ║
╚══════════════════════════════════════════════╝

  [1] Login to Account
  [2] Create New Account
  [3] Admin Panel
  [0] Exit

Enter choice: 1

──────────────────────────────────────────────
  Account Number : SA001
  PIN            : ****
──────────────────────────────────────────────

╔══════════════════════════════════════════════╗
║  Account: SA001  |  Rashid  |  Savings      ║
║  Balance: Rs. 45,230.00                      ║
╚══════════════════════════════════════════════╝

  [1] Deposit
  [2] Withdraw
  [3] Fund Transfer
  [4] Transaction History
  [5] Mini Statement
  [6] Loan Services
  [7] Manage Beneficiaries
  [0] Logout

──────────────────── MINI STATEMENT ──────────────────
  Date         Type         Amount       Balance
  ─────────────────────────────────────────────────
  19-May-2026  CREDIT       +10,000      45,230
  18-May-2026  DEBIT         -5,000      35,230
  17-May-2026  TRANSFER     -2,000       40,230
  16-May-2026  EMI DEBIT   -10,624       42,230
  ─────────────────────────────────────────────────
```

---

## 🚀 Installation & Setup

```bash
# Step 1 — Clone the repository
git clone https://github.com/rashid/banking-management-system.git

# Step 2 — Navigate to project
cd banking-management-system

# Step 3 — Compile all files
javac -d out src/**/*.java src/Main.java

# Step 4 — Run
java -cp out Main
```

**Requirements:**
- Java 17 or above
- Any terminal / IDE (VS Code, IntelliJ)

---

## 📚 Learning Outcomes

After building this project, you will understand:

| Concept | Where Applied |
|---------|--------------|
| Abstract Classes | `Account.java` base class |
| Inheritance | `SavingsAccount`, `CurrentAccount`, `FDAccount` |
| Polymorphism | `withdraw()` behaves differently per account type |
| Encapsulation | Private `balance`, `pin`, controlled via methods |
| Custom Exceptions | `InsufficientBalanceException`, `AccountLockedException` |
| Singleton Pattern | `BankingSystem` — only one instance |
| Factory Pattern | `AccountFactory` — create accounts without `new` |
| Strategy Pattern | `InterestCalculator` — swap algorithms at runtime |
| File I/O | CSV persistence — data survives restart |
| Collections | `HashMap`, `ArrayList`, `TreeMap` — right tool for right job |

---

## 🛣️ Future Enhancements

- [ ] **MySQL Database** — Replace CSV with JDBC + MySQL
- [ ] **Spring Boot REST API** — `POST /accounts`, `GET /transactions`
- [ ] **JWT Authentication** — Token-based security
- [ ] **Web UI** — React frontend
- [ ] **Unit Tests** — JUnit 5 test coverage
- [ ] **Docker** — Containerize the application
- [ ] **Notifications** — Email/SMS on transactions

---

## ⚠️ Known Limitations

- Data stored in CSV — not suitable for concurrent users
- No real encryption on PIN (should use BCrypt in production)
- Console UI only — no graphical interface
- Single-threaded — no concurrent transaction handling

---

## 🧪 Test Coverage

| Module | Scenarios Tested |
|--------|-----------------|
| Authentication | Valid PIN, Wrong PIN, Account Lock |
| Deposit | Valid amount, Zero amount, Negative amount |
| Withdraw | Sufficient balance, Insufficient balance, Limit exceeded |
| Transfer | Success, Insufficient balance, Invalid account, Rollback |
| Loan | Apply, EMI calculation, Admin approval |

---

## 🤝 Contributing

1. Fork the repository
2. Create your branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -m "Add your feature"`
4. Push: `git push origin feature/your-feature`
5. Open a Pull Request

---

## 📄 License

This project is licensed under the **MIT License** — feel free to use and modify.

---

## 👨‍💻 Author

**Md. Rashid**
- 📧 Email: rashiid.cse@gmail.com
- 🐙 GitHub: [@rashid](https://github.com/rashid)
- 📚 Learning Path: Core Java → Java 8 → Spring Boot → Microservices

---

## 🙏 Acknowledgments

- *Head First Java* by Kathy Sierra — for making OOP concepts click
- *Clean Code* by Robert C. Martin — for writing better code
- Java Documentation — the ultimate reference

---

> *Built as part of the **Backend Engineering Path** — Core Java Capstone*
> *Pure Java 17+ — zero external frameworks.*

---

⭐ **If this project helped you, please star the repo!** ⭐
