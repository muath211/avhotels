package com.avhotels.clients.besthotel.mapping;

import com.avhotels.clients.besthotel.BestHotel;
import com.avhotels.clients.besthotel.BestHotelSearchRequest;
import com.avhotels.clients.besthotel.BestHotelSearchResponse;
import com.avhotels.config.DefaultClientsConfigurations;
import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.model.Hotel;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BestHotelMapperTest {

    private BestHotelMapper bestHotelMapper;

    @Before
    public void setUp() {
        DefaultClientsConfigurations clientsConfigurations = new DefaultClientsConfigurations();
        clientsConfigurations.setBestHotelsProvider("BestHotels");
        bestHotelMapper = new BestHotelMapper(clientsConfigurations);
    }

    @Test
    public void mapRequest_NullInput_EmptyResult() {
        BestHotelSearchRequest bestHotelSearchRequest = bestHotelMapper.mapRequest(null);

        assertNotNull(bestHotelSearchRequest);
    }

    @Test
    public void mapRequest_SampleRequest_RequestMapped() {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        hotelSearchRequest.setCity("AMM");
        hotelSearchRequest.setFromDate(new Date());
        hotelSearchRequest.setToDate(new Date());
        hotelSearchRequest.setNumberOfAdults((short) 2);

        BestHotelSearchRequest bestHotelSearchRequest = bestHotelMapper.mapRequest(hotelSearchRequest);

        assertNotNull(bestHotelSearchRequest);
        assertNotNull(bestHotelSearchRequest.getCity());
        assertNotNull(bestHotelSearchRequest.getFromDate());
        assertNotNull(bestHotelSearchRequest.getToDate());
    }

    @Test
    public void mapResponse_NullInput_EmptyResult() {
        List<Hotel> hotels = bestHotelMapper.mapResponse(null, null);

        assertNotNull(hotels);
        assertEquals(hotels.size(), 0);
    }

    @Test
    public void mapResponse() {
        BestHotelSearchResponse bestHotelSearchResponse = new BestHotelSearchResponse();
        BestHotel bestHotel = new BestHotel();
        bestHotel.setName("Sheraton");
        bestHotel.setPrice(34.3);
        bestHotel.setRate((short) 3);
        bestHotel.setRoomAmenities("Hair Dryer, Balcony");

        bestHotelSearchResponse.setHotels(Collections.singletonList(bestHotel));

        BestHotelSearchRequest bestHotelSearchRequest = new BestHotelSearchRequest.Builder()
                .from(Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .to(Date.from(LocalDate.now().plusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        List<Hotel> hotels = bestHotelMapper.mapResponse(bestHotelSearchRequest, bestHotelSearchResponse);

        assertNotNull(hotels);
        assertEquals(hotels.size(), 1);
        assertNotNull(hotels.get(0).getName());
        assertNotNull(hotels.get(0).getProvider());
        assertNotEquals(hotels.get(0).getFare(), 0);
        assertNotNull(hotels.get(0).getAmenities());

        assertEquals(hotels.get(0).getAmenities().size(), bestHotel.getRoomAmenities().split(",").length);
    }
}