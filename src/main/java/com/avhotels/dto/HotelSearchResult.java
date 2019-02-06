package com.avhotels.dto;

import com.avhotels.model.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelSearchResult {
    private List<Hotel> hotels = new ArrayList<>();

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
    }

    public void addHotels(List<Hotel> hotel) {
        this.hotels.addAll(hotel);
    }

    @Override
    public String toString() {
        return "HotelSearchResult{" +
                "hotels=" + hotels +
                '}';
    }
}
