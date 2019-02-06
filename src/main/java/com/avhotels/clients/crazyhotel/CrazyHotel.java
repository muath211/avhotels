package com.avhotels.clients.crazyhotel;

import java.util.Arrays;

public class CrazyHotel {

    private String name;

    private String city;

    private String rate; // String of '*' from 1-5, Eg: *, *****

    private double price; // Price of the hotel per night

    private double discount; // discount on the room (if available).

    private String[] amenities; //array of strings

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String[] getAmenities() {
        return amenities;
    }

    public void setAmenities(String[] amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "CrazyHotel{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", rate='" + rate + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", amenities=" + Arrays.toString(amenities) +
                '}';
    }
}
