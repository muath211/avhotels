package com.avhotels.clients.besthotel;

import com.avhotels.clients.BaseClient;
import com.avhotels.clients.besthotel.mapping.BestHotelMapper;
import com.avhotels.config.Endpoints;
import com.avhotels.mapping.AvHotelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
            return new BestHotelSearchResponse();
        }
        LOGGER.debug("searching for hotels in [" + endpoints.getBestHotelUrl() + "]");

        ResponseEntity<BestHotelSearchResponse> searchResult;
        try {
            searchResult = restTemplate.postForEntity(endpoints.getBestHotelUrl(), bestHotelSearchRequest, BestHotelSearchResponse.class);
        } catch (Exception e) {
            LOGGER.error("Failed to call client [" + endpoints.getBestHotelUrl() + "]. ", e);
            return new EmptyResponse();
        }

        if (searchResult.getBody() == null) {
            return new EmptyResponse();
        }
        return searchResult.getBody();
    }

    @Override
    protected AvHotelMapper<BestHotelSearchRequest, BestHotelSearchResponse> getMapper() {
        return hotelMapper;
    }


}
