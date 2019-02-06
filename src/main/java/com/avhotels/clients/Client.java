package com.avhotels.clients;

import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.model.Hotel;

import java.util.List;

/**
 * hotel-provider
 */
public interface Client {

    /**
     *
     * abstraction for hotel providers, third-party hotel providers
     * should implement this interface to do their calls.
     *
     * @param hotelSearchRequest
     * @return
     */
    List<Hotel> findHotel(HotelSearchRequest hotelSearchRequest);

}
