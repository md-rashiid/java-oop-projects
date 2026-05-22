class DoubleRoom extends Room{
    DoubleRoom(String roomNumber,double pricePerNight){
        super(roomNumber,pricePerNight);
    }
    @Override
    public String getRoomType(){
        return "Double Room";
    }
    @Override
    public int getCapacity(){
        return 2;
    }
    @Override
    public void displayInfo() {
    System.out.println("ROOM NUMBER : " + getRoomNumber());
    System.out.println("TYPE        : " + getRoomType());
    System.out.println("CAPACITY    : " + getCapacity());
    System.out.println("PRICE/NIGHT : Rs." + getPricePerNight());
    System.out.println("AVAILABLE   : " + isAvailable());
}

}