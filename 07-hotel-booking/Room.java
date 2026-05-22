public abstract class Room {

    private String roomNumber;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(String roomNumber, double pricePerNight) {
        this.roomNumber    = roomNumber;
        this.pricePerNight = pricePerNight;
        this.isAvailable   = true; // default — naya room available hota hai
    }

    // Abstract Methods — subclass override karegi
    public abstract String getRoomType();
    public abstract int getCapacity();
    public abstract void displayInfo();

    // Normal Getters
    public String getRoomNumber()    { return roomNumber; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable()     { return isAvailable; }

    // Setter — booking pe false, checkout pe true
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
