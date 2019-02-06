package com.avhotels.clients;

import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.mapping.AvHotelMapper;
import com.avhotels.model.Hotel;

import java.util.List;

/**
 * template for third-party client calls.
 *
 * @param <R>
 * @param <S>
 */
public abstract class BaseClient<R, S> implements Client {

    public List<Hotel> findHotel(HotelSearchRequest hotelSearchRequest) {
        R request = getMapper().mapRequest(hotelSearchRequest);
        S response = findHotels(request);
        return getMapper().mapResponse(request, response);
    }

    protected abstract S findHotels(R request);

    protected abstract AvHotelMapper<R, S> getMapper();



}
