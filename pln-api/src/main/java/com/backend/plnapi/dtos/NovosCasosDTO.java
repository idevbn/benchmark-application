package com.backend.plnapi.dtos;

import lombok.Data;

@Data
public class NovosCasosDTO {

    private long newCasesFirstCountry;
    private long newCasesSecondCountry;

    public static NovosCasosDTO fromFirst(final long firstCountry) {
        final NovosCasosDTO dto = new NovosCasosDTO();
        dto.setNewCasesFirstCountry(firstCountry);

        return dto;
    }

    public static NovosCasosDTO fromSecond(final long secondCountry) {
        final NovosCasosDTO dto = new NovosCasosDTO();
        dto.setNewCasesSecondCountry(secondCountry);

        return dto;
    }

}
