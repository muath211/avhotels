package com.avhotels.mapping;

import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.model.Hotel;

import java.util.List;

/**
 *
 * used to map R Request, S Response for different third-party hotel providers,
 * internally called, see @{@link com.avhotels.clients.BaseClient}
 *
 */
public interface AvHotelMapper<R, S> {

    R mapRequest(HotelSearchRequest hotelSearchRequest);

    List<Hotel> mapResponse(R request, S response);

}
