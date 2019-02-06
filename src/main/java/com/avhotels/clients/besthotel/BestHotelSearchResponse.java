package com.avhotels.clients.besthotel;

import java.util.ArrayList;
import java.util.List;

public class BestHotelSearchResponse {

    private List<BestHotel> hotels = new ArrayList<>();

    public List<BestHotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<BestHotel> hotels) {
        this.hotels = hotels;
    }

    @Override
    public String toString() {
        return "BestHotelSearchResponse{" +
                "hotels=" + hotels +
                '}';
    }
}
