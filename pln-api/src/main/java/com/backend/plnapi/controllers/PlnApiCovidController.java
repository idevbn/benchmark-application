package com.backend.plnapi.controllers;

import com.backend.plnapi.clients.CovidApiClient;
import com.backend.plnapi.dtos.CovidDataDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class PlnApiCovidController {

    private final CovidApiClient covidApiClient;

    public PlnApiCovidController(final CovidApiClient covidApiClient) {
        this.covidApiClient = covidApiClient;
    }

    @GetMapping(value = "/covid")
    public ResponseEntity<CovidDataDTO[]> getAllCovidData(
            @RequestParam(required = false)
            final String country,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @RequestParam(required = false)
            final LocalDate date,
            @RequestParam(required = false)
            final String region,
            @RequestParam(required = false)
            final String county,
            @RequestParam(required = false)
            final String type
    ) throws JsonProcessingException {
        return this.covidApiClient.getAllCovidData(
                country, date, region, county, type
        );
    }

}
