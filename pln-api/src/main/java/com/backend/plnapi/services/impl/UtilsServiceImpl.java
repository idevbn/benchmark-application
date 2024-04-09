package com.backend.plnapi.services.impl;

import com.backend.plnapi.services.UtilsService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
public class UtilsServiceImpl implements UtilsService {

    private static final String DEFAULT_COUNTRY_NAME = "brazil";

    /**
     * Método que recebe os request params e retorna
     * a url com base nos parâmetros fornecidos.
     * <p>
     * Caso nenhum parâmetro seja fornecido, por default,
     * será construída uma url com o parâmetro coutry=brazil.
     */
    @Override
    public String createUrl(String country,
                            final LocalDate date,
                            final String region,
                            final String county,
                            final String type) {
        final HashMap<String, String> params = new HashMap<>();

        if (country == null && date == null) {
            country = DEFAULT_COUNTRY_NAME;
        }

        params.put("country", country);
        params.put("date", date != null ? date.toString() : null);
        params.put("region", region);
        params.put("county", county);
        params.put("type", type);

        final StringBuilder urlBuilder = new StringBuilder();

        boolean firstParam = true;

        for (final Map.Entry<String, String> entry : params.entrySet()) {
            final String key = entry.getKey();
            final String value = entry.getValue();

            if (key != null && value != null) {
                urlBuilder.append(firstParam ? "?" : "&")
                        .append(key)
                        .append("=")
                        .append(value);
                firstParam = false;
            }
        }

        return urlBuilder.toString();
    }

}
