package com.backend.plnapi.services;

import java.time.LocalDate;

public interface UtilsService {

    String createUrl(final String countryName,
                     final LocalDate date,
                     final String region,
                     final String county,
                     final String type);

}
