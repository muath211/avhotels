package com.avhotels.clients.besthotel;

public class BestHotel {

    private String name;

    private short rate; // Number from 1-5

    private double price; // Total price rounded to 2 decimals

    private String roomAmenities; //String of amenities separated by comma

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getRate() {
        return rate;
    }

    public void setRate(short rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRoomAmenities() {
        return roomAmenities;
    }

    public void setRoomAmenities(String roomAmenities) {
        this.roomAmenities = roomAmenities;
    }
}
