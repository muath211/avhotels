package com.avhotels.clients.crazyhotel;

import com.avhotels.clients.BaseClient;
import com.avhotels.clients.crazyhotel.mapping.CrazyHotelMapper;
import com.avhotels.config.Endpoints;
import com.avhotels.mapping.AvHotelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class CrazyHotelsClient extends BaseClient<CrazyHotelSearchRequest, CrazyHotelSearchResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrazyHotelsClient.class);
    private RestTemplate restTemplate;
    private Endpoints endpoints;
    private CrazyHotelMapper hotelMapper;

    public CrazyHotelsClient(RestTemplate restTemplate, Endpoints endpoints, CrazyHotelMapper hotelMapper) {
        this.restTemplate = restTemplate;
        this.endpoints = endpoints;
        this.hotelMapper = hotelMapper;
    }


    @Override
    protected CrazyHotelSearchResponse findHotels(CrazyHotelSearchRequest crazyHotelSearchRequest) {
        if (crazyHotelSearchRequest == null) {
            return new EmptyResponse();
        }
        LOGGER.debug("searching for hotels in [" + endpoints.getCrazyHotelUrl() + "]");

        ResponseEntity<CrazyHotelSearchResponse> searchResult;
        try {
            searchResult = restTemplate.postForEntity(endpoints.getCrazyHotelUrl(), crazyHotelSearchRequest, CrazyHotelSearchResponse.class);
        } catch (Exception e) {
            LOGGER.error("Failed to call client [" + endpoints.getCrazyHotelUrl() + "]. ", e);
            return new EmptyResponse();
        }

        return Optional.ofNullable(searchResult).map(HttpEntity::getBody).orElse(new EmptyResponse());
    }

    @Override
    protected AvHotelMapper<CrazyHotelSearchRequest, CrazyHotelSearchResponse> getMapper() {
        return hotelMapper;
    }


}
