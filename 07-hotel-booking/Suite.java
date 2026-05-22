class Suite extends Room{
     private boolean hasJacuzzi;

     Suite(String roomNumber, double pricePerNight,boolean hasJacuzzi){
        super(roomNumber,pricePerNight);
        this.hasJacuzzi=hasJacuzzi;
     }
     @Override
     public  String getRoomType(){
        return "Suite";
     }
     @Override
     public int getCapacity(){
        return 4;
     }
     @Override
     public void displayInfo(){
        System.out.println("ROOM NUMBER: " + getRoomNumber());
        System.out.println("TYPE: " + getRoomType());
        System.out.println("CAPACITY: " + getCapacity());
        System.out.println("PRICE/NIGHT: " + getPricePerNight());
        System.out.println("AVAILABLE: " + isAvailable());
        System.out.println("Has JACUZZI: " + hasJacuzzi);
     }
}