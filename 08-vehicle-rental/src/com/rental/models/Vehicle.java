package com.rental.models;
 public abstract class Vehicle{
    private  String vehicleId;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

     public Vehicle(String vehicleId,String brand,String model,double pricePerDay){
        this.vehicleId=vehicleId;
        this.brand=brand;
        this.model=model;
        this.pricePerDay=pricePerDay;
        this.isAvailable=true;
    }

    public abstract String getVehicleType();
    public abstract String getFuelType();
    public abstract void display();

    public String getVehicleId(){
        return vehicleId;
    }
    public String getBrand(){
         return brand;
    }
    public String getModel(){
        return model;
    }
    public double getPricePerDay(){
        return pricePerDay;
    }
    public boolean isAvailable(){
     return isAvailable;
    }
    public void setAvailable(boolean available){
           this.isAvailable=available;
    }


}