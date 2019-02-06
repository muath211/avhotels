package com.avhotels.mapping;

import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.model.Hotel;

import java.util.List;

/**
 *
 * used to map R Request, S Response for different third-party hotel providers,
 * internally called see @{@link com.avhotels.clients.BaseClient}
 *
 *
 * @param <R>
 * @param <S>
 */
public interface AvHotelMapper<R, S> {

    /**
     * @param hotelSearchRequest
     * @return
     */
    R mapRequest(HotelSearchRequest hotelSearchRequest);


    /**
     * @param request
     * @param response
     * @return
     */
    List<Hotel> mapResponse(R request, S response);

}
