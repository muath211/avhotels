package com.avhotels.controller;


import com.avhotels.dto.HotelSearchRequest;
import com.avhotels.dto.HotelSearchResult;
import com.avhotels.service.search.HotelsSearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class HotelsController {

    @Autowired
    private HotelsSearchService hotelsSearchService;

    @ApiOperation(httpMethod = "POST", value = "Find available hotels", response = HotelSearchResult.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Available hotels", response = HotelSearchResult.class),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping("AvailableHotel")
    @ResponseBody
    public HotelSearchResult availableHotels(@RequestBody @Valid HotelSearchRequest hotelSearchRequest) {
        return hotelsSearchService.findHotels(hotelSearchRequest);
    }


}
