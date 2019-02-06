package com.avhotels.clients.crazyhotel;

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
public class CrazyHotelsClientTest {

    @Autowired
    private CrazyHotelsClient crazyHotelsClient;

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
        CrazyHotelSearchResponse actualResponse = crazyHotelsClient.findHotels(null);

        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getHotels());
        assertEquals(actualResponse.getHotels().size(), 0);
    }

    @Test
    public void findHotels_HotelEndpointCalled_ReturnsResult() throws Exception {
        CrazyHotelSearchResponse expectedResponse = createExpectedResponse();
        String hotelsResponse = objectMapper.writeValueAsString(expectedResponse);

        mockServer.expect(MockRestRequestMatchers.requestTo("http://localhost:8081/crazy/CrazyHotels"))
                .andRespond(MockRestResponseCreators.withSuccess(hotelsResponse, MediaType.APPLICATION_JSON));

        CrazyHotelSearchResponse actualResponse = crazyHotelsClient.findHotels(new CrazyHotelSearchRequest());

        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getHotels());
        assertEquals(actualResponse.getHotels().size(), expectedResponse.getHotels().size());
        assertEquals(actualResponse.getHotels().get(0).getName(), expectedResponse.getHotels().get(0).getName());
        assertEquals(actualResponse.getHotels().get(0).getPrice(), expectedResponse.getHotels().get(0).getPrice(), 0.001);
        assertEquals(actualResponse.getHotels().get(0).getRate(), expectedResponse.getHotels().get(0).getRate());
        assertEquals(actualResponse.getHotels().get(0).getAmenities().length, expectedResponse.getHotels().get(0).getAmenities().length);
    }

    private CrazyHotelSearchResponse createExpectedResponse() {
        CrazyHotelSearchResponse expectedResponse = new CrazyHotelSearchResponse();
        CrazyHotel hotel = new CrazyHotel();
        hotel.setName("AlaaDeen");
        hotel.setRate("**");
        hotel.setPrice(31.4);
        hotel.setAmenities(new String[]{"Hair Dryer, Television"});

        expectedResponse.setHotels(Collections.singletonList(hotel));
        return expectedResponse;
    }

}