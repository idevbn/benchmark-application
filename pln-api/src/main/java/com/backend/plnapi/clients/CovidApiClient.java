package com.backend.plnapi.clients;

import com.backend.plnapi.beans.ApiCredentials;
import com.backend.plnapi.dtos.CovidDataDTO;
import com.backend.plnapi.services.UtilsService;
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
    public ResponseEntity<Object> getAllCovidData(final String country,
                                                  final LocalDate date,
                                                  final String region,
                                                  final String county,
                                                  final String type) {

        ResponseEntity<CovidDataDTO[]> result = null;

        final String url = this.apiCredentials.getApiCovidRequestUrl()
                + this.utilsService.createUrl(
                country, date, region, county, type
        );

        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);

        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", this.apiCredentials.getApiKey());
        final HttpEntity<String> requestEntity = new HttpEntity<>("parameters", headers);

        result = this.restTemplate
                .exchange(url, HttpMethod.GET, requestEntity, CovidDataDTO[].class);

        log.info("Ending request /covid ");
        return ResponseEntity.status(HttpStatus.OK).body(result.getBody());
    }

}
