package com.avhotels.clients.crazyhotel;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CrazyHotelSearchRequest {

    private String city; //IATA code (AUH)

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss'Z'")
    private Date fromDate; // ISO_INSTANT

    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss'Z'")
    private Date toDate; // ISO_INSTANT

    private int adultsCount; // integer number

    public String getCity() {
        return city;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public int getAdultsCount() {
        return adultsCount;
    }

    @Override
    public String toString() {
        return "CrazyHotelSearchRequest{" +
                "city='" + city + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", adultsCount=" + adultsCount +
                '}';
    }

    public static class Builder {
        private CrazyHotelSearchRequest crazyHotelSearchRequest;

        public Builder() {
            crazyHotelSearchRequest = new CrazyHotelSearchRequest();
        }

        public Builder city(String city) {
            crazyHotelSearchRequest.city = city;
            return this;
        }

        public Builder from(Date from) {
            crazyHotelSearchRequest.fromDate = from;
            return this;
        }

        public Builder to(Date to) {
            crazyHotelSearchRequest.toDate = to;
            return this;
        }

        public Builder adultsCount(int adultsCount) {
            crazyHotelSearchRequest.adultsCount = adultsCount;
            return this;
        }

        public CrazyHotelSearchRequest build() {
            return crazyHotelSearchRequest;
        }

    }

}
