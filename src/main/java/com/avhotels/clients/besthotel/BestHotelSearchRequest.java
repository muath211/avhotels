package com.avhotels.clients.besthotel;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BestHotelSearchRequest {

    private String city; //IATA code (AUH)

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fromDate; // ISO_LOCAL_DATE

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date toDate; // ISO_LOCAL_DATE

    private short numberOfAdults; // integer number

    public String getCity() {
        return city;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public short getNumberOfAdults() {
        return numberOfAdults;
    }

    @Override
    public String toString() {
        return "BestHotelSearchRequest{" +
                "city='" + city + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", numberOfAdults=" + numberOfAdults +
                '}';
    }

    public static class Builder {
        private BestHotelSearchRequest bestHotelSearchRequest;

        public Builder() {
            bestHotelSearchRequest = new BestHotelSearchRequest();
        }

        public Builder city(String city) {
            bestHotelSearchRequest.city = city;
            return this;
        }

        public Builder from(Date from) {
            bestHotelSearchRequest.fromDate = from;
            return this;
        }

        public Builder to(Date to) {
            bestHotelSearchRequest.toDate = to;
            return this;
        }

        public Builder numberOfAdults(short numberOfAdults) {
            bestHotelSearchRequest.numberOfAdults = numberOfAdults;
            return this;
        }

        public BestHotelSearchRequest build() {
            return bestHotelSearchRequest;
        }

    }
}
