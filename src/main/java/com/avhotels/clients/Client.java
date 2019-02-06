package com.avhotels.clients;

import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.model.Hotel;

import java.util.List;

/**
 * third-party hotel provider
 */
public interface Client {

    List<Hotel> findHotel(HotelSearchRequest hotelSearchRequest);

}
