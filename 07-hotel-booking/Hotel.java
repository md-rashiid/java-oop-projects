import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;

class Hotel {
    private String hotelName;
    private HashMap<String, Room> rooms;
    private HashMap<String, Guest> guests;
    private ArrayList<Booking> bookings;
    private int bookingCounter;

    Hotel(String hotelName) {
        this.hotelName      = hotelName;
        this.rooms          = new HashMap<>();
        this.guests         = new HashMap<>();
        this.bookings       = new ArrayList<>();
        this.bookingCounter = 1;
    }
    public void addRoom(Room room) {
        rooms.put(room.getRoomNumber(), room);
        System.out.println("Room added: " + room.getRoomNumber());
    }
    public void registerGuest(Guest guest){
        guests.put(guest.getGuestId(),guest);
        System.out.println("Guest registered:  " + guest.getName());

    }
    public void bookRoom(String guestId, String roomNumber,
                         LocalDate checkIn, LocalDate checkOut) {
        Guest guest = guests.get(guestId);
        Room room   = rooms.get(roomNumber);

        if (guest == null)              { System.out.println("Guest not found");     return; }
        if (room == null)               { System.out.println("Room not found");      return; }
        if (!room.isAvailable())        { System.out.println("Room not available");  return; }
        if (!checkOut.isAfter(checkIn)) { System.out.println("Invalid dates");       return; }

        room.setAvailable(false);
        String bookingId = "BKG" + bookingCounter++;
        bookings.add(new Booking(bookingId, guestId, roomNumber, checkIn, checkOut));
        System.out.println("Booking confirmed: " + bookingId);
    }

    public void cancelBooking(String bookingId) {
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId) && b.isActive()) {
                b.cancel();
                rooms.get(b.getRoomNumber()).setAvailable(true);
                System.out.println("Booking cancelled: " + bookingId);
                return;
            }
        }
        System.out.println("Booking not found.");
    }

    public void checkOut(String bookingId) {
        for (Booking b : bookings) {
            if (b.getBookingId().equals(bookingId) && b.isActive()) {
                Room room   = rooms.get(b.getRoomNumber());
                double bill = b.calculateBill(room.getPricePerNight());
                room.setAvailable(true);
                System.out.println("Checked out! Bill: Rs." + bill);
                return;
            }
        }
        System.out.println("Booking not found.");
    }

    public void displayAvailableRooms() {
        for (Room room : rooms.values()) {
            if (room.isAvailable()) room.displayInfo();
        }
    }

    public void displayAllBookings() {
        for (Booking b : bookings) b.displayInfo();
    }
}

