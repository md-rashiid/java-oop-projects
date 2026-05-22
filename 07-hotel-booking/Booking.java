import java.time.LocalDate;

class Booking {
    private String bookingId;
    private String guestId;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean isCancelled;

    Booking(String bookingId, String guestId, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        this.bookingId    = bookingId;
        this.guestId      = guestId;
        this.roomNumber   = roomNumber;
        this.checkInDate  = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isCancelled  = false;
    }

    public String getBookingId()       { return bookingId; }
    public String getGuestId()         { return guestId; }
    public String getRoomNumber()      { return roomNumber; }
    public LocalDate getCheckInDate()  { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public boolean isCancelled()       { return isCancelled; }

    public double calculateBill(double pricePerNight) {
        long nights = checkInDate.until(checkOutDate).getDays();
        return nights * pricePerNight;
    }

    public void cancel() {
        isCancelled = true;
    }

    public boolean isActive() {
        return !isCancelled;
    }

    public void displayInfo() {
        System.out.println("------------- Booking Info -------------");
        System.out.println("BOOKING ID   : " + bookingId);
        System.out.println("GUEST ID     : " + guestId);
        System.out.println("ROOM NUMBER  : " + roomNumber);
        System.out.println("CHECK IN     : " + checkInDate);
        System.out.println("CHECK OUT    : " + checkOutDate);
        System.out.println("IS CANCELLED : " + isCancelled);
    }
}