package com.avhotels.clients.besthotel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BestHotelsClientTest {


    @Autowired
    private BestHotelsClient bestHotelsClient;

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void findHotels_NullInput_EmptyResult() {
        BestHotelSearchResponse actualResponse = bestHotelsClient.findHotels(null);

        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getHotels());
        assertEquals(actualResponse.getHotels().size(), 0);
    }

    @Test
    public void findHotels_HotelEndpointCalled_ReturnsResult() throws Exception {
        BestHotelSearchResponse expectedResponse = createExpectedResponse();
        String hotelsResponse = objectMapper.writeValueAsString(expectedResponse);

        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8082/best/BestHotels"))
                .andRespond(MockRestResponseCreators.withSuccess(hotelsResponse, MediaType.APPLICATION_JSON));

        BestHotelSearchResponse actualResponse = bestHotelsClient.findHotels(new BestHotelSearchRequest());

        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getHotels());
        assertEquals(actualResponse.getHotels().size(), expectedResponse.getHotels().size());
        assertEquals(actualResponse.getHotels().get(0).getName(), expectedResponse.getHotels().get(0).getName());
        assertEquals(actualResponse.getHotels().get(0).getPrice(), expectedResponse.getHotels().get(0).getPrice(), 0.001);
        assertEquals(actualResponse.getHotels().get(0).getRate(), expectedResponse.getHotels().get(0).getRate());
        assertEquals(actualResponse.getHotels().get(0).getRoomAmenities(), expectedResponse.getHotels().get(0).getRoomAmenities());
    }

    private BestHotelSearchResponse createExpectedResponse() {
        BestHotelSearchResponse expectedResponse = new BestHotelSearchResponse();
        BestHotel hotel = new BestHotel();
        hotel.setName("Crown Plaza");
        hotel.setRate((short) 2);
        hotel.setPrice(33.4);
        hotel.setRoomAmenities("Hair Dryer, Iron");

        expectedResponse.setHotels(Collections.singletonList(hotel));
        return expectedResponse;
    }
}