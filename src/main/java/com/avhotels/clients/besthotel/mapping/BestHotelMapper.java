package com.avhotels.clients.besthotel.mapping;

import com.avhotels.clients.besthotel.AdditionalProperties;
import com.avhotels.clients.besthotel.BestHotel;
import com.avhotels.clients.besthotel.BestHotelSearchRequest;
import com.avhotels.clients.besthotel.BestHotelSearchResponse;
import com.avhotels.config.ClientsConfigurations;
import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.mapping.AvHotelMapper;
import com.avhotels.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BestHotelMapper implements AvHotelMapper<BestHotelSearchRequest, BestHotelSearchResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BestHotelMapper.class);
    private static final String DELIMITER = ",";
    private ClientsConfigurations clientsConfigurations;

    public BestHotelMapper(ClientsConfigurations clientsConfigurations) {
        this.clientsConfigurations = clientsConfigurations;
    }

    @Override
    public BestHotelSearchRequest mapRequest(HotelSearchRequest hotelSearchRequest) {
        if (hotelSearchRequest == null) {
            return new BestHotelSearchRequest();
        }
        LOGGER.debug("mapping request hotelSearchRequest [" + hotelSearchRequest.toString() + "] ...");

        return new BestHotelSearchRequest.Builder()
                .city(hotelSearchRequest.getCity())
                .from(hotelSearchRequest.getFromDate())
                .to(hotelSearchRequest.getToDate())
                .numberOfAdults(hotelSearchRequest.getNumberOfAdults())
                .build();
    }

    @Override
    public List<Hotel> mapResponse(BestHotelSearchRequest bestHotelSearchRequest, BestHotelSearchResponse bestHotelSearchResponse) {
        if (bestHotelSearchRequest == null || bestHotelSearchResponse == null) {
            return new ArrayList<>();
        }
        LOGGER.debug("mapping response bestHotelSearchResponse [" + bestHotelSearchResponse.toString() + "] ...");

        int period = calculatePeriod(bestHotelSearchRequest);

        return bestHotelSearchResponse.getHotels().stream().map((BestHotel bestHotel) -> mapValues(bestHotel, period)).collect(Collectors.toList());
    }

    private int calculatePeriod(BestHotelSearchRequest bestHotelSearchRequest) {
        LocalDate from = bestHotelSearchRequest.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate to = bestHotelSearchRequest.getToDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(from, to).getDays();
    }

    private Hotel mapValues(BestHotel bestHotel, int stayPeriod) {
        Hotel hotel = new Hotel();
        hotel.setProvider(clientsConfigurations.getBestHotelsProvider());
        hotel.setName(bestHotel.getName());
        hotel.setFare(calculatePrice(bestHotel, stayPeriod));
        hotel.setAmenities(getAmenities(bestHotel));
        hotel.setRate(bestHotel.getRate());
        hotel.addAdditionalProperty(AdditionalProperties.RATE.key(), bestHotel.getRate());
        return hotel;
    }

    private List<String> getAmenities(BestHotel bestHotel) {
        String roomAmenities = bestHotel.getRoomAmenities();
        if (roomAmenities != null) {
            return Arrays.asList(roomAmenities.split(DELIMITER));
        }
        return new ArrayList<>();
    }

    private double calculatePrice(BestHotel bh, int stayPeriod) {
        return bh.getPrice() / stayPeriod;
    }
}
