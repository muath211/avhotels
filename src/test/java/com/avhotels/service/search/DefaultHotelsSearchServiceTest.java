package com.avhotels.service.search;

import com.avhotels.clients.Client;
import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.dto.HotelSearchResult;
import com.avhotels.model.Hotel;
import com.avhotels.registry.ClientsRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DefaultHotelsSearchServiceTest {


    @Mock
    private ClientsRegistry clientsRegistry;

    @InjectMocks
    private DefaultHotelsSearchService defaultHotelsSearchService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findHotels_NullInput_EmptyResult() {
        HotelSearchResult hotels = defaultHotelsSearchService.findHotels(null);

        Assert.assertNotNull(hotels);
        Assert.assertNotNull(hotels.getHotels());
        Assert.assertEquals(hotels.getHotels().size(), 0);
    }

    @Test
    public void findHotels_TwoClients_ReturnsHotelsForTwoClients() {
        List<Client> clients = Arrays.asList(new DummyClient(), new DummyClient());
        Mockito.when(clientsRegistry.getClients()).thenReturn(clients);


        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        HotelSearchResult hotels = defaultHotelsSearchService.findHotels(hotelSearchRequest);

        int hotelsCount = clients.stream().map(c -> c.findHotel(hotelSearchRequest).size()).mapToInt(Integer::intValue).sum();

        Assert.assertNotNull(hotels.getHotels());
        Assert.assertEquals(hotels.getHotels().size(), hotelsCount);
    }


    class DummyClient implements Client {

        @Override
        public List<Hotel> findHotel(HotelSearchRequest hotelSearchRequest) {
            return Collections.singletonList(new Hotel());
        }
    }

}