package com.avhotels.service.search;

import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.dto.HotelSearchResult;
import com.avhotels.model.Hotel;
import com.avhotels.registry.ClientsRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

@Component
public class DefaultHotelsSearchService implements HotelsSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHotelsSearchService.class);

    private ClientsRegistry clientsRegistry;

    public DefaultHotelsSearchService(ClientsRegistry clientsRegistry) {
        this.clientsRegistry = clientsRegistry;
    }

    public HotelSearchResult findHotels(HotelSearchRequest hotelSearchRequest) {
        if (hotelSearchRequest == null) {
            LOGGER.info("invalid hotelSearchRequest with value of null.");
            return new HotelSearchResult();
        }
        LOGGER.debug("finding hotels for [" + hotelSearchRequest.toString() + "]");

        HotelSearchResult hotelSearchResult = new HotelSearchResult();
        Optional.ofNullable(clientsRegistry.getClients())
                .ifPresent(clients -> clients.forEach(c -> hotelSearchResult.addHotels(c.findHotel(hotelSearchRequest))));

        hotelSearchResult.getHotels().sort(Comparator.comparingInt(Hotel::getRate).reversed());

        LOGGER.debug("[" + hotelSearchResult.getHotels().size() + "] hotels found for [" + hotelSearchRequest.toString() + "]");
        return hotelSearchResult;
    }


}
