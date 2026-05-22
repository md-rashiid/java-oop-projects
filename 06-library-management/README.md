# 📚 Library Management System — Java OOP Project

A fully functional Library Management System built in pure Java.
This project simulates a real library — books can be added, members can register,
borrow books, return them, and fines are auto-calculated for late returns.

Built to practice core Java OOP concepts — Encapsulation, Composition,
and real-world object modeling with HashMap-based inventory management.

---

## ✨ Features

| Feature | What it does |
|---------|-------------|
| 📖 Add Books | Add books with title, author, genre, and copy count |
| 👤 Register Members | Register library members with contact details |
| 📤 Issue Book | Issue a book to a member with auto due date (14 days) |
| 📥 Return Book | Return a book and auto-calculate fine if late |
| 🔍 Search | Search books by title (case-insensitive) |
| 📊 Display | View all books, members, and borrow records |
| ✅ Validations | Invalid member, invalid book, no copies available — all handled |
| 💰 Fine System | Rs. 5 per day for late returns |

---

## 🗂️ Project Structure

```
06-library-management/
│
├── Book.java          ← Book details (id, title, author, genre, copies)
├── Member.java        ← Member details + list of borrowed books
├── BorrowRecord.java  ← Transaction record (who, what, when, fine)
├── Library.java       ← Core logic (issue, return, search, display)
└── Main.java          ← Full demo — runs all scenarios
```

---

## 🧠 OOP Concepts Used

### 1. Encapsulation
Every field in every class is `private`. Data is accessed only through
`public` getters and setters. For example, `Book.availableCopies` is private —
it can only be changed through `setAvailableCopies()`. This prevents invalid
data like negative stock.

```java
// Nobody can do this from outside ❌
book.availableCopies = -5;

// They must use the setter ✅
book.setAvailableCopies(book.getAvailableCopies() - 1);
```

### 2. Composition (HAS-A relationship)
Objects are built by combining other objects:

```
Library    HAS-A  HashMap<String, Book>      (book catalog)
Library    HAS-A  HashMap<String, Member>    (member list)
Library    HAS-A  ArrayList<BorrowRecord>    (all transactions)
Member     HAS-A  ArrayList<Book>            (books currently borrowed)
```

### 3. Single Responsibility Principle
Each class has one job:

```
Book.java         → store book information only
Member.java       → store member information only
BorrowRecord.java → store one transaction + calculate fine
Library.java      → manage everything (issue, return, search)
```

`borrowBook()` and `returnBook()` are NOT in `Book.java` — because a book
does not issue itself. The Library does. This is real-world thinking.

### 4. HashMap vs ArrayList — Why both?

```java
// Books and Members → HashMap (fast lookup by ID)
HashMap<String, Book> books;
books.get("B001"); // direct access — O(1)

// BorrowRecords → ArrayList (need to loop and filter)
ArrayList<BorrowRecord> borrowRecords;
// because we search by memberId + bookId + isReturned condition
```

---

## 🔗 Class Relationships

```
Library
  ├── HAS-A → HashMap<String, Book>
  ├── HAS-A → HashMap<String, Member>
  └── HAS-A → ArrayList<BorrowRecord>
                    │
                    ├── stores memberId (String)
                    └── stores bookId   (String)

Member
  └── HAS-A → ArrayList<Book>  (currently borrowed)

BorrowRecord
  ├── issueDate  = today
  ├── dueDate    = today + 14 days
  ├── returnDate = null (until returned)
  └── fine       = daysLate × Rs.5
```

---

## 💡 Key Design Decisions

**Why does BorrowRecord store String IDs instead of objects?**
Because this mirrors how a real database works — you store foreign keys (IDs),
not the entire record. When you need the full object, you look it up from the
HashMap. This keeps BorrowRecord lightweight and decoupled.

**Why is fine calculated in BorrowRecord and not Library?**
Because fine depends only on `dueDate` and `returnDate` — both of which
belong to BorrowRecord. It is BorrowRecord's responsibility to know its own fine.
Library just reads the result. This is the Single Responsibility Principle.

**Why auto-set issueDate and dueDate in BorrowRecord constructor?**
Because every borrow record always starts today and is due in 14 days.
There is no reason to pass these from outside — the constructor handles it.
This is safe encapsulation.

---

## 🚀 How to Run

```bash
# Step 1 — Go to project folder
cd ~/Documents/Backend\ Engineering\ Path/Projects/01-Core-Java/18-Mini-Projects/src/06-library-management

# Step 2 — Compile
javac *.java

# Step 3 — Run
java Main
```

**Requirement:** Java 8+

---

## 📋 Demo Walkthrough (Main.java)

| Step | Action |
|------|--------|
| 1 | Library `City Public Library` created |
| 2 | 4 books added (2 Programming, 1 Fiction, 1 Self Help) |
| 3 | 3 members registered |
| 4-5 | All books and members displayed |
| 6 | 3 books issued — Aman & Priya get same book (different copies) |
| 7 | Validation tested — invalid member ID, invalid book ID |
| 8 | Search by title `"java"` — finds Head First Java |
| 9 | 2 books returned — fine checked (Rs.0 since returned same day) |
| 10 | Return validation — already returned book shows correct message |
| 11 | Final inventory — available copies correctly updated |

---

## 🛣️ What Can Be Added Next

- **Exception Handling** — Custom `BookNotFoundException`, `MemberNotFoundException`
- **File I/O** — Save and load records from a `.txt` file
- **Limit per member** — Max 3 books at a time
- **Overdue check** — Show all currently overdue books
- **Spring Boot** — Convert to REST API with `GET /books`, `POST /issue`

---

## 👨‍💻 Author

**Md. Rashid**
- 📚 Learning Path: Core Java → Java 8 → Spring Boot
- 🗓️ Completed: May 2026

---

> *Built as part of the **Backend Engineering Path** — Core Java OOP Module*
> *Pure Java — zero external libraries or frameworks.*
