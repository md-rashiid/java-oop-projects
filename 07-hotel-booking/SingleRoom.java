class SingleRoom extends Room{
    SingleRoom(String roomNumber,double pricePerNight){
        super(roomNumber,pricePerNight);
    }
    @Override
     public String getRoomType(){
         return "Single Room";
    }
    @Override
    public int getCapacity(){
        return 1;
    }
    @Override
    public void displayInfo(){
        System.out.println("ROOM NUMBER: " + getRoomNumber());
        System.out.println("TYPE: " + getRoomType());
        System.out.println("CAPACITY: " + getCapacity());
        System.out.println("PRICE/NIGHT: " + getPricePerNight());
        System.out.println("AVAILABLE: " + isAvailable());
    }
}