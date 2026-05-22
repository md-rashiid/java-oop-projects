# Hotel Booking System — Java OOP Project

---

## Project Structure / Project ki Files

```
07-hotel-booking/
├── Room.java          → Abstract base class (Parent of all rooms)
├── SingleRoom.java    → Room ka child (capacity: 1)
├── DoubleRoom.java    → Room ka child (capacity: 2)
├── Suite.java         → Room ka child (capacity: 4, hasJacuzzi)
├── Guest.java         → Guest ki details store karta hai
├── Booking.java       → Ek booking ka poora record
├── Hotel.java         → Sab kuch manage karta hai (Brain)
└── Main.java          → Program ka entry point (Testing)
```

---

## 1. Room.java — Abstract Class

**English:**
`Room` is the parent class for all room types. It is `abstract` — meaning you
cannot create a plain `Room` object. It holds common fields (roomNumber,
pricePerNight, isAvailable) and declares 3 abstract methods that every child
class MUST override.

**Hindi:**
`Room` sabhi room types ki parent class hai. Yeh `abstract` hai — matlab directly
`new Room()` nahi bana sakte. Isme saare rooms ke common fields hain aur 3 methods
`abstract` hain — har child class ko yeh methods khud likhne PADENGE.

```java
// Yeh nahi ho sakta:
Room r = new Room("101", 1500); // ERROR — abstract class

// Yeh hoga:
Room r = new SingleRoom("101", 1500); // OK
```

**Abstract methods — child class must override:**
```java
public abstract String getRoomType(); // "Single Room" / "Double Room" / "Suite"
public abstract int getCapacity();    // 1 / 2 / 4
public abstract void displayInfo();   // har room ka alag format
```

---

## 2. SingleRoom / DoubleRoom / Suite — Child Classes

**English:**
These 3 classes extend `Room`. They inherit all fields and methods of `Room`
for free and only write what makes them unique. `Suite` has an extra field
`hasJacuzzi`. `super()` is used to call the parent constructor.

**Hindi:**
Yeh 3 classes `Room` ko `extend` karti hain. `Room` ke saare fields aur methods
inhe FREE mein milte hain. Sirf apni unique cheezein likhti hain. `Suite` mein
extra field `hasJacuzzi` bhi hai. `super()` se parent ka constructor call hota hai.

```java
class Suite extends Room {
    private boolean hasJacuzzi; // only Suite ka apna extra field

    Suite(String roomNumber, double pricePerNight, boolean hasJacuzzi) {
        super(roomNumber, pricePerNight); // Room ka constructor call
        this.hasJacuzzi = hasJacuzzi;
    }

    @Override
    public String getRoomType() { return "Suite"; }

    @Override
    public int getCapacity() { return 4; }
}
```

---

## 3. Guest.java — Simple Data Class

**English:**
Stores guest information — guestId, name, phone, email. No business logic.
Just data + getters + displayInfo().

**Hindi:**
Guest ki basic info store karta hai — sirf data, koi logic nahi.
Hotel class isko HashMap mein store karti hai fast lookup ke liye.

```java
private String guestId;
private String name;
private String phone;
private String email;
```

---

## 4. Booking.java — Booking Record

**English:**
One booking = one object of this class. It knows who booked (guestId),
which room (roomNumber), check-in/out dates. It can calculate the bill
and cancel itself.

**Hindi:**
Ek booking = is class ka ek object. Kisne book kiya, kaun sa room,
kab se kab tak — sab yahan. Bill calculate karna aur cancel karna
isi ki zimmedaari hai.

```java
// Bill calculate karna:
public double calculateBill(double pricePerNight) {
    long nights = checkInDate.until(checkOutDate).getDays();
    return nights * pricePerNight;
    // 3 nights x Rs.1500 = Rs.4500
}
```

**Why `isCancelled = false` in constructor? / Kyun default false?**

English: Every new booking is active. No reason to pass it from outside.
Hindi: Naya booking hamesha active hota hai — constructor mein fix hai, parameter lena galat hoga.

---

## 5. Hotel.java — The Brain / Manager

**English:**
This is the most important class. It uses `HashMap` for fast lookup of
rooms and guests, and `ArrayList` for all bookings.

**Hindi:**
Yeh sabse important class hai. Rooms aur Guests `HashMap` mein hain
(ID se seedha milte hain), Bookings `ArrayList` mein hain (loop karke dhundthe hain).

### HashMap vs ArrayList — Kyun alag alag?

```
Rooms aur Guests ke liye HashMap:
  rooms.get("101")    → seedha Room 101 milti hai — FAST

Bookings ke liye ArrayList:
  for (Booking b : bookings) { ... }  → loop karke match karte hain
```

| | HashMap | ArrayList |
|--|---------|-----------|
| Use kab | ID se direct access | Loop karke filter |
| Speed | O(1) instant | O(n) loop |
| Is project | rooms, guests | bookings |

### bookRoom() — Step by step:

```java
public void bookRoom(String guestId, String roomNumber,
                     LocalDate checkIn, LocalDate checkOut) {

    // Step 1: Guest dhundo
    Guest guest = guests.get(guestId);
    if (guest == null) { System.out.println("Guest not found"); return; }

    // Step 2: Room dhundo
    Room room = rooms.get(roomNumber);
    if (room == null) { System.out.println("Room not found"); return; }

    // Step 3: Available hai?
    if (!room.isAvailable()) { System.out.println("Room not available"); return; }

    // Step 4: Sab sahi — booking karo
    room.setAvailable(false);                              // room block karo
    String bookingId = "BKG" + bookingCounter++;           // ID banao
    bookings.add(new Booking(bookingId, guestId,
                             roomNumber, checkIn, checkOut)); // record save karo
}
```

---

## 6. OOP Concepts Used / OOP Concepts jo use hue

### Abstraction
English: `Room` is abstract — nobody can create a plain Room object.
Hindi: `Room` abstract hai — koi directly `new Room()` nahi bana sakta.
Isse ensure hota hai ki hamesha specific room type hi banega.

### Inheritance
English: SingleRoom, DoubleRoom, Suite extend Room and get its fields free.
Hindi: Teeno classes `Room` se inherit karti hain — common code ek jagah,
har class sirf apni unique cheez likhti hai.

### Polymorphism
English: `Hotel` stores all room types in `HashMap<String, Room>`.
Hindi: HashMap mein `Room` type hai lekin andar `SingleRoom`, `DoubleRoom`,
`Suite` — sab store ho sakte hain. Ek type, alag alag forms.

```java
HashMap<String, Room> rooms = new HashMap<>();
rooms.put("101", new SingleRoom("101", 1500)); // SingleRoom as Room
rooms.put("201", new DoubleRoom("201", 2500)); // DoubleRoom as Room
rooms.put("301", new Suite("301", 5000, true)); // Suite as Room
```

### Encapsulation
English: All fields are `private`. Change only via getters/setters.
Hindi: Saare fields `private` — direct access nahi, sirf methods se change hoga.

```java
room.isAvailable = false; // ERROR — private
room.setAvailable(false); // OK — setter se
```

### Composition (HAS-A)
English: Hotel has rooms, guests, bookings as its own objects.
Hindi: Hotel ke paas rooms, guests, bookings hain — yeh HAS-A relationship hai.

```
Hotel  HAS-A  HashMap<String, Room>
Hotel  HAS-A  HashMap<String, Guest>
Hotel  HAS-A  ArrayList<Booking>
```

---

## How to Run / Kaise chalayein

```bash
# Is folder mein jao
cd path/to/07-hotel-booking

# Compile karo
javac *.java

# Run karo
java Main
```

---

## Sample Output

```
Room added: 101
Guest registered: Ali
Booking confirmed: BKG1
Room not available        ← validation kaam kiya
Booking cancelled: BKG3  ← cancel kaam kiya
Checked out! Bill: Rs.4500.0  ← 3 nights x 1500 = 4500
```

---

*Project: Backend Engineering Path — Core Java OOP Module*
*Author: Md. Rashid | Completed: May 2026*
