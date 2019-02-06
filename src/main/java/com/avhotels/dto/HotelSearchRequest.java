package com.avhotels.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

public class HotelSearchRequest {

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent
    private Date fromDate; //  ISO_LOCAL_DATE

//    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future
    private Date toDate; // ISO_LOCAL_DATE

    @Size(max = 3)
    private String city; //  IATA code (AUH)

    @Min(1)
    private short numberOfAdults; // integer number

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public short getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(short numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    @Override
    public String toString() {
        return "HotelSearchRequest{" +
                "fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", city='" + city + '\'' +
                ", numberOfAdults=" + numberOfAdults +
                '}';
    }
}
