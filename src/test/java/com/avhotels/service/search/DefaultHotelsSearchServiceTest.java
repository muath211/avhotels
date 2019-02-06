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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void findHotels_TwoClientsWithRates_ReturnsSortedHotels() {
        Hotel hotel = new Hotel();
        hotel.setRate(1);

        Hotel hotel2 = new Hotel();
        hotel2.setRate(4);

        Hotel hotel3 = new Hotel();
        hotel3.setRate(2);

        List<Hotel> sortedHotels = Arrays.asList(hotel, hotel2, hotel3);

        List<Client> clients = Arrays.asList(hotelSearchRequest -> Collections.singletonList(hotel), hotelSearchRequest -> Arrays.asList(hotel2, hotel3));
        Mockito.when(clientsRegistry.getClients()).thenReturn(clients);


        HotelSearchRequest hotelSearchRequest = new HotelSearchRequest();
        HotelSearchResult searchResult = defaultHotelsSearchService.findHotels(hotelSearchRequest);

        int hotelsCount = clients.stream().map(c -> c.findHotel(hotelSearchRequest).size()).mapToInt(Integer::intValue).sum();

        Assert.assertNotNull(searchResult.getHotels());
        Assert.assertEquals(searchResult.getHotels().size(), hotelsCount);

        List<Integer> actualRates = searchResult.getHotels().stream().map(Hotel::getRate).collect(Collectors.toList());
        List<Integer> expectedRates = sortedHotels.stream().map(Hotel::getRate).sorted(Comparator.comparingInt(Integer::intValue).reversed())
                .collect(Collectors.toList());

        Assert.assertEquals(expectedRates, actualRates);
    }


    class DummyClient implements Client {

        @Override
        public List<Hotel> findHotel(HotelSearchRequest hotelSearchRequest) {
            return Collections.singletonList(new Hotel());
        }
    }

}