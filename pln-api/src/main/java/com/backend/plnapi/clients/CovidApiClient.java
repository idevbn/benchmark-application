package com.backend.plnapi.clients;

import com.backend.plnapi.beans.ApiCredentials;
import com.backend.plnapi.dtos.CovidDataCountryDTO;
import com.backend.plnapi.dtos.CovidDataDTO;
import com.backend.plnapi.dtos.CovidDataDefaultDTO;
import com.backend.plnapi.services.UtilsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

/**
 * Client responsável por realizar requisição à API externa.
 */
@Log4j2
@Component
public class CovidApiClient {

    private final RestTemplate restTemplate;
    private final UtilsService utilsService;
    private final ApiCredentials apiCredentials;

    public CovidApiClient(final RestTemplate restTemplate,
                          final UtilsService utilsService,
                          final ApiCredentials apiCredentials) {
        this.restTemplate = restTemplate;
        this.utilsService = utilsService;
        this.apiCredentials = apiCredentials;
    }
    public ResponseEntity<CovidDataDTO[]> getAllCovidData(
            final String country,
            final LocalDate date,
            final String region,
            final String county,
            final String type
    ) throws JsonProcessingException {
        final String url = this.apiCredentials.getApiCovidRequestUrl()
                + this.utilsService.createUrl(
                country, date, region, county, type
        );

        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);

        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", this.apiCredentials.getApiKey());
        final HttpEntity<String> requestEntity = new HttpEntity<>("parameters", headers);

        final ResponseEntity<String> response = this.restTemplate
                .exchange(url, HttpMethod.GET, requestEntity, String.class);

        final ObjectMapper mapper = new ObjectMapper();

        CovidDataDTO[] result;

        if (country != null) {
            result = mapper.readValue(response.getBody(), CovidDataCountryDTO[].class);
        } else {
            result = mapper.readValue(response.getBody(), CovidDataDefaultDTO[].class);
        }

        log.info("Ending request /covid ");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * Método que recupera dados da API externa referentes a filtagem por
     * um país.
     */
    public CovidDataDTO getDataByCountry(final String countryName) throws JsonProcessingException {
        final ResponseEntity<CovidDataDTO[]> dataFromCountry = this
                .getAllCovidData(countryName, null, null, null, null);

        final CovidDataDTO[] dataBody = dataFromCountry.getBody();

        return dataBody[0];
    }

}
