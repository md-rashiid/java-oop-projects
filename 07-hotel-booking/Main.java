import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // ── Hotel banao ──────────────────────────────────────────
        Hotel hotel = new Hotel("Grand Hotel");

        // ── Rooms alag alag banao ────────────────────────────────
        SingleRoom r1 = new SingleRoom("101", 1500);
        SingleRoom r2 = new SingleRoom("102", 1500);
        DoubleRoom r3 = new DoubleRoom("201", 2500);
        DoubleRoom r4 = new DoubleRoom("202", 2500);
        Suite      r5 = new Suite("301", 5000, true);

        // Hotel mein add karo
        hotel.addRoom(r1);
        hotel.addRoom(r2);
        hotel.addRoom(r3);
        hotel.addRoom(r4);
        hotel.addRoom(r5);

        // ── Guests alag alag banao ───────────────────────────────
        Guest g1 = new Guest("G001", "Ali",   "9111111111", "ali@gmail.com");
        Guest g2 = new Guest("G002", "Sara",  "9222222222", "sara@gmail.com");
        Guest g3 = new Guest("G003", "Rahul", "9333333333", "rahul@gmail.com");

        // Hotel mein register karo
        hotel.registerGuest(g1);
        hotel.registerGuest(g2);
        hotel.registerGuest(g3);

        // ── Available rooms dekho ────────────────────────────────
        System.out.println("\n--- Available Rooms ---");
        hotel.displayAvailableRooms();

        // ── Dates banao ──────────────────────────────────────────
        LocalDate checkIn1  = LocalDate.of(2026, 5, 20);
        LocalDate checkOut1 = LocalDate.of(2026, 5, 23); // 3 nights

        LocalDate checkIn2  = LocalDate.of(2026, 5, 21);
        LocalDate checkOut2 = LocalDate.of(2026, 5, 25); // 4 nights

        LocalDate checkIn3  = LocalDate.of(2026, 5, 22);
        LocalDate checkOut3 = LocalDate.of(2026, 5, 26); // 4 nights

        // ── Rooms book karo ──────────────────────────────────────
        System.out.println("\n--- Bookings ---");
        hotel.bookRoom("G001", "101", checkIn1, checkOut1); // Ali → Room 101
        hotel.bookRoom("G002", "201", checkIn2, checkOut2); // Sara → Room 201
        hotel.bookRoom("G003", "301", checkIn3, checkOut3); // Rahul → Suite 301

        // ── Validation — booked room dobara book karo ────────────
        System.out.println("\n--- Validation Test ---");
        hotel.bookRoom("G002", "101", checkIn1, checkOut1); // Room 101 already booked!

        // ── Available rooms after booking ────────────────────────
        System.out.println("\n--- Available Rooms After Booking ---");
        hotel.displayAvailableRooms();

        // ── Cancel karo ──────────────────────────────────────────
        System.out.println("\n--- Cancel Booking ---");
        hotel.cancelBooking("BKG3"); // Rahul ka booking cancel

        // ── Checkout karo ────────────────────────────────────────
        System.out.println("\n--- Checkout ---");
        hotel.checkOut("BKG1"); // Ali checkout → 3 nights × 1500 = Rs.4500

        // ── Sab bookings dekho ───────────────────────────────────
        System.out.println("\n--- All Bookings ---");
        hotel.displayAllBookings();
    }
}
