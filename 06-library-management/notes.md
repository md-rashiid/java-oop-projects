# Mini Projects

## What is it?
Mini projects help you apply everything you have learned in Core Java. Building real programs solidifies your understanding of OOP, collections, exception handling, file I/O, and more. Below are four beginner-friendly project ideas with key concepts each one covers.

## Project 1: Library Management System

### What You Build
A console app to manage books — add, remove, search, borrow, and return books.

### Key Concepts Used
- Classes and Objects (Book, Library, Member)
- ArrayList to store books and members
- Methods for add, remove, search operations
- Exception handling for invalid inputs
- File I/O to save/load data

### Basic Structure
```java
public class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    // constructors, getters, setters
}

public class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) { books.add(book); }
    public void removeBook(String title) { /* find and remove */ }
    public Book searchBook(String title) { /* search and return */ }
    public void displayAllBooks() { /* loop and print */ }
}
```

## Project 2: ATM Simulator

### What You Build
A console app simulating ATM operations — check balance, deposit, withdraw, transfer, and view transaction history.

### Key Concepts Used
- Encapsulation (private balance with public methods)
- Switch-case for menu navigation
- Custom exceptions (InsufficientFundsException)
- Loops for continuous menu display
- ArrayList for transaction history

### Basic Structure
```java
public class ATM {
    private double balance;
    private ArrayList<String> history = new ArrayList<>();

    public void deposit(double amount) { /* add to balance, log */ }
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) throw new InsufficientFundsException("...");
        // subtract and log
    }
    public void showHistory() { /* print all transactions */ }
}
```

## Project 3: Student Grade Management

### What You Build
A system to manage students, record their grades, calculate averages, and generate report cards.

### Key Concepts Used
- HashMap to map students to their grades
- Arrays for storing multiple subject scores
- Methods for calculating average, min, max
- Sorting students by grade (Comparable/Comparator)
- File I/O to export report cards

### Basic Structure
```java
public class Student {
    private String name;
    private HashMap<String, Double> grades = new HashMap<>();

    public void addGrade(String subject, double score) { /* put */ }
    public double getAverage() { /* calculate from grades */ }
    public String getLetterGrade() { /* A, B, C based on average */ }
}
```

## Project 4: Quiz App

### What You Build
A console-based quiz game with multiple-choice questions, scoring, and a timer or attempt limit.

### Key Concepts Used
- Arrays or ArrayList for storing questions
- OOP (Question class with options and correct answer)
- Scanner for user input
- Control flow for checking answers
- String manipulation for formatting

### Basic Structure
```java
public class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctOption;
    }
}

public class Quiz {
    private ArrayList<Question> questions = new ArrayList<>();
    private int score = 0;

    public void startQuiz() { /* loop through questions, get input */ }
    public void showResult() { /* print score */ }
}
```

## Tips for Building Projects

### Planning
- Start by listing all features you want
- Draw a simple class diagram (what classes do you need?)
- Start small — get the basic version working first, then add features

### Coding
- Write one feature at a time and test it before moving on
- Use meaningful variable and method names
- Keep methods short — each method should do one thing
- Handle exceptions — do not let the program crash on bad input

### Testing
- Test with normal inputs, edge cases, and invalid inputs
- Try entering wrong data types (letters when expecting numbers)
- Test with empty inputs and boundary values

### Common Beginner Patterns
```java
// Menu-driven program pattern
Scanner scanner = new Scanner(System.in);
while (true) {
    System.out.println("1. Add  2. Remove  3. View  4. Exit");
    int choice = scanner.nextInt();
    switch (choice) {
        case 1 -> add();
        case 2 -> remove();
        case 3 -> view();
        case 4 -> { System.out.println("Goodbye!"); return; }
        default -> System.out.println("Invalid choice");
    }
}
```

## Quick Summary
Mini projects are the best way to practice Core Java. Start with a Library Management System or ATM Simulator to apply OOP, collections, and exception handling. The Student Grade Management project adds HashMap and sorting practice. The Quiz App is great for arrays, loops, and string handling. Always plan before coding, build incrementally, and test thoroughly.
