package com.avhotels.clients.crazyhotel;

import java.util.ArrayList;
import java.util.List;

public class EmptyResponse extends CrazyHotelSearchResponse {

    @Override
    public List<CrazyHotel> getHotels() {
        return new ArrayList<>();
    }
}
