package com.backend.plnapi.dtos;

import lombok.Data;

@Data
public class TotalCasosDTO {

    private long totalFirstCountry;
    private long totalSecondCountry;

    public static TotalCasosDTO fromFirst(final long firstCountry) {
        final TotalCasosDTO dto = new TotalCasosDTO();
        dto.setTotalFirstCountry(firstCountry);

        return dto;
    }

    public static TotalCasosDTO fromSecond(final long secondCountry) {
        final TotalCasosDTO dto = new TotalCasosDTO();
        dto.setTotalSecondCountry(secondCountry);

        return dto;
    }

}
