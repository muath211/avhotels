package com.avhotels.service.search;

import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.dto.HotelSearchResult;

public interface HotelsSearchService {

    HotelSearchResult findHotels(HotelSearchRequest hotelSearchRequest);
}
