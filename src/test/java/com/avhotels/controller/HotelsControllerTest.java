package com.avhotels.controller;

import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.dto.HotelSearchResult;
import com.avhotels.service.search.HotelsSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelsController.class)
public class HotelsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelsSearchService hotelsSearchService;

    @InjectMocks
    private HotelsController hotelsController;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(hotelsSearchService.findHotels(any(HotelSearchRequest.class)))
                .thenReturn(new HotelSearchResult());
    }


    @Test
    public void availableHotels_NullRequest_ReturnsBadRequest() throws Exception {
        HotelSearchRequest hotelSearchRequest = null;

        mockMvc.perform(MockMvcRequestBuilders.post("/AvailableHotel", hotelSearchRequest))
                .andExpect(status().is(400))
                .andReturn();
    }

    @Test
    public void availableHotels_CalledWithInvalidAdultsCount_ReturnsBadRequest() throws Exception {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        hotelSearchRequest.setFromDate(Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setToDate(Date.from(LocalDate.now().plusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setNumberOfAdults((short) 0);
        hotelSearchRequest.setCity("AMM");

        post(hotelSearchRequest, 400);
    }

    @Test
    public void availableHotels_CalledWithInvalidFromDate_ReturnsBadRequest() throws Exception {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        hotelSearchRequest.setFromDate(Date.from(LocalDate.now().minusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setToDate(Date.from(LocalDate.now().plusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setNumberOfAdults((short) 2);
        hotelSearchRequest.setCity("AMM");

        post(hotelSearchRequest, 400);
    }

    @Test
    public void availableHotels_CalledWithInvalidToDate_ReturnsBadRequest() throws Exception {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        hotelSearchRequest.setFromDate(Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setToDate(Date.from(LocalDate.now().minusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setNumberOfAdults((short) 2);
        hotelSearchRequest.setCity("AMM");

        post(hotelSearchRequest, 400);
    }

    @Test
    public void availableHotels_CalledWithInvalidCity_ReturnsBadRequest() throws Exception {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        hotelSearchRequest.setFromDate(Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setToDate(Date.from(LocalDate.now().plusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setNumberOfAdults((short) 2);
        hotelSearchRequest.setCity("invalidcity");

        post(hotelSearchRequest, 400);
    }

    @Test
    public void availableHotels_CalledWithValidSampleRequest_ReturnsValidResponse() throws Exception {
        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        hotelSearchRequest.setFromDate(Date.from(LocalDate.now().plusDays(10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setToDate(Date.from(LocalDate.now().plusDays(20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        hotelSearchRequest.setNumberOfAdults((short) 2);
        hotelSearchRequest.setCity("AMM");


        post(hotelSearchRequest, 200);
    }

    private void post(HotelSearchRequest hotelSearchRequest, int status) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/AvailableHotel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hotelSearchRequest)))
                .andExpect(status().is(status))
                .andReturn();
    }
}