package com.avhotels.clients.crazyhotel;

import java.util.ArrayList;
import java.util.List;

public class CrazyHotelSearchResponse {

    private List<CrazyHotel> hotels = new ArrayList<>();

    public List<CrazyHotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<CrazyHotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public String toString() {
        return "CrazyHotelSearchResponse{" +
                "hotels=" + hotels +
                '}';
    }
}
