package com.avhotels.clients.crazyhotel.mapping;

import com.avhotels.clients.crazyhotel.CrazyHotel;
import com.avhotels.clients.crazyhotel.CrazyHotelSearchRequest;
import com.avhotels.clients.crazyhotel.CrazyHotelSearchResponse;
import com.avhotels.config.DefaultClientsConfigurations;
import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.model.Hotel;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class CrazyHotelMapperTest {

    private CrazyHotelMapper crazyHotelMapper;

    @Before
    public void setUp() {
        DefaultClientsConfigurations clientsConfigurations = new DefaultClientsConfigurations();
        clientsConfigurations.setCrazyHotelsProvider("CrazyHotles");
        crazyHotelMapper = new CrazyHotelMapper(clientsConfigurations);
    }

    @Test
    public void mapRequest_NullInput_EmptyResult() {
        CrazyHotelSearchRequest crazyHotelSearchRequest = crazyHotelMapper.mapRequest(null);

        assertNotNull(crazyHotelSearchRequest);
    }

    @Test
    public void mapRequest_SampleRequest_RequestMapped() {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        hotelSearchRequest.setCity("AMM");
        hotelSearchRequest.setFromDate(new Date());
        hotelSearchRequest.setToDate(new Date());
        hotelSearchRequest.setNumberOfAdults(2);

        CrazyHotelSearchRequest crazyHotelSearchRequest = crazyHotelMapper.mapRequest(hotelSearchRequest);

        assertNotNull(crazyHotelSearchRequest);
        assertNotNull(crazyHotelSearchRequest.getCity());
        assertNotNull(crazyHotelSearchRequest.getFromDate());
        assertNotNull(crazyHotelSearchRequest.getToDate());
    }

    @Test
    public void mapResponse_NullInput_EmptyResult() {
        List<Hotel> hotels = crazyHotelMapper.mapResponse(null, null);

        assertNotNull(hotels);
        assertEquals(hotels.size(), 0);
    }

    @Test
    public void mapResponse() {
        CrazyHotelSearchResponse bestHotelSearchResponse = new CrazyHotelSearchResponse();
        CrazyHotel crazyHotel = new CrazyHotel();
        crazyHotel.setName("Helton");
        crazyHotel.setPrice(34.3);
        crazyHotel.setRate("****");
        crazyHotel.setAmenities(new String[]{"Crazy Towels", "Bar"});

        bestHotelSearchResponse.setHotels(Collections.singletonList(crazyHotel));

        List<Hotel> hotels = crazyHotelMapper.mapResponse(new CrazyHotelSearchRequest(), bestHotelSearchResponse);

        assertNotNull(hotels);
        assertEquals(hotels.size(), 1);
        assertNotNull(hotels.get(0).getName());
        assertNotNull(hotels.get(0).getProvider());
        assertNotEquals(hotels.get(0).getFare(), 0);
        assertNotNull(hotels.get(0).getAmenities());

        assertEquals(hotels.get(0).getAmenities().size(), crazyHotel.getAmenities().length);
    }
}