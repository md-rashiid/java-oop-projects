class Guest{
    private String guestId;
    private String name;
    private String phone;
    private String email;

    Guest(String guestId,String name,String phone,String email){
        this.guestId=guestId;
        this.name=name;
        this.phone=phone;
        this.email=email;
    }
    public String getGuestId(){
        return guestId;
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String  getEmail(){
        return email;
    }
    public void displayInfo(){
        System.out.println("-----------------------Guest Info-------------------");
        System.out.println("GUEST ID: " + guestId);
        System.out.println("NAME: " + name);
        System.out.println("PHONE: " + phone);
        System.out.println("EMAIL: " + email);
        
    }

    

}