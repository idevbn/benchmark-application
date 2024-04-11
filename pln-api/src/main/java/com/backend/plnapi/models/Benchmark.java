package com.backend.plnapi.models;

import com.backend.plnapi.dtos.ResultadosDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "benchmarks")
public class Benchmark {

    @Id
    private String id;

    @Field(name = "benchmark")
    private final String benchMarkName;

    @Field(name = "first_country_name")
    private final String firstCountryName;

    @Field(name = "second_country_name")
    private final String secondCountryName;

    @Field(name = "data_inicial")
    private final LocalDate dataInicial;

    @Field(name = "data_final")
    private final LocalDate dataFinal;

    @Field(name = "resultados")
    private final ResultadosDTO resultados;

    public static Benchmark of(final String benchMarkName,
                               final String firstCountryName,
                               final String secondCountryName,
                               final String dataInicial,
                               final String dataFinal,
                               final ResultadosDTO resultados) {
        final Benchmark benchmark = new Benchmark(
                benchMarkName,
                firstCountryName,
                secondCountryName,
                LocalDate.parse(dataInicial),
                LocalDate.parse(dataFinal),
                resultados
        );

        return benchmark;
    }

}
