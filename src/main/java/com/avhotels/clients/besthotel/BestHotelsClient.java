package com.avhotels.clients.besthotel;

import com.avhotels.clients.BaseClient;
import com.avhotels.clients.besthotel.mapping.BestHotelMapper;
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
public class BestHotelsClient extends BaseClient<BestHotelSearchRequest, BestHotelSearchResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BestHotelsClient.class);
    private RestTemplate restTemplate;
    private Endpoints endpoints;
    private BestHotelMapper hotelMapper;

    public BestHotelsClient(RestTemplate restTemplate, Endpoints endpoints, BestHotelMapper hotelMapper) {
        this.restTemplate = restTemplate;
        this.endpoints = endpoints;
        this.hotelMapper = hotelMapper;
    }

    @Override
    protected BestHotelSearchResponse findHotels(BestHotelSearchRequest bestHotelSearchRequest) {
        if (bestHotelSearchRequest == null) {
            return new EmptyResponse();
        }
        LOGGER.debug("searching for hotels in [" + endpoints.getBestHotelUrl() + "]");

        ResponseEntity<BestHotelSearchResponse> searchResult;
        try {
            searchResult = restTemplate.postForEntity(endpoints.getBestHotelUrl(), bestHotelSearchRequest, BestHotelSearchResponse.class);
        } catch (Exception e) {
            LOGGER.error("Failed to call client [" + endpoints.getBestHotelUrl() + "]. ", e);
            return new EmptyResponse();
        }

        return Optional.ofNullable(searchResult).map(HttpEntity::getBody).orElse(new EmptyResponse());
    }

    @Override
    protected AvHotelMapper<BestHotelSearchRequest, BestHotelSearchResponse> getMapper() {
        return hotelMapper;
    }


}
