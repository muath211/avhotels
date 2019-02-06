package com.avhotels.clients.crazyhotel.mapping;

import com.avhotels.clients.crazyhotel.AdditionalProperties;
import com.avhotels.clients.crazyhotel.CrazyHotel;
import com.avhotels.clients.crazyhotel.CrazyHotelSearchRequest;
import com.avhotels.clients.crazyhotel.CrazyHotelSearchResponse;
import com.avhotels.config.ClientsConfigurations;
import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.mapping.AvHotelMapper;
import com.avhotels.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CrazyHotelMapper implements AvHotelMapper<CrazyHotelSearchRequest, CrazyHotelSearchResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrazyHotelMapper.class);
    private ClientsConfigurations clientsConfigurations;

    public CrazyHotelMapper(ClientsConfigurations clientsConfigurations) {
        this.clientsConfigurations = clientsConfigurations;
    }

    @Override
    public CrazyHotelSearchRequest mapRequest(HotelSearchRequest hotelSearchRequest) {
        if (hotelSearchRequest == null) {
            return new CrazyHotelSearchRequest();
        }
        LOGGER.debug("mapping request hotelSearchRequest [" + hotelSearchRequest.toString() + "] ...");

        return new CrazyHotelSearchRequest.Builder()
                .city(hotelSearchRequest.getCity())
                .from(hotelSearchRequest.getFromDate())
                .to(hotelSearchRequest.getToDate())
                .adultsCount(hotelSearchRequest.getNumberOfAdults())
                .build();
    }

    @Override
    public List<Hotel> mapResponse(CrazyHotelSearchRequest crazyHotelSearchRequest, CrazyHotelSearchResponse crazyHotelSearchResponse) {
        if (crazyHotelSearchRequest == null || crazyHotelSearchResponse == null) {
            return new ArrayList<>();
        }
        LOGGER.debug("mapping request hotelSearchRequest [" + crazyHotelSearchResponse.toString() + "] ...");

        return crazyHotelSearchResponse.getHotels().stream().map(this::mapValues).collect(Collectors.toList());
    }

    private Hotel mapValues(CrazyHotel crazyHotel) {
        Hotel hotel = new Hotel();
        hotel.setProvider(clientsConfigurations.getCrazyHotelsProvider());
        hotel.setName(crazyHotel.getName());
        hotel.setFare(crazyHotel.getPrice());
        hotel.setRate(Optional.ofNullable(crazyHotel.getRate()).map(String::length).orElse(0));
        hotel.setAmenities(Arrays.asList(crazyHotel.getAmenities()));
        hotel.addAdditionalProperty(AdditionalProperties.RATE.key(), crazyHotel.getRate());
        hotel.addAdditionalProperty(AdditionalProperties.DISCOUNT.key(), crazyHotel.getDiscount());
        return hotel;
    }
}
