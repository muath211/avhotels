package com.avhotels.clients.besthotel;

import java.util.ArrayList;
import java.util.List;

public class EmptyResponse extends BestHotelSearchResponse {

    @Override
    public List<BestHotel> getHotels() {
        return new ArrayList<>();
    }
}
