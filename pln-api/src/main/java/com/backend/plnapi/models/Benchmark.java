package com.backend.plnapi.models;

import com.backend.plnapi.dtos.ResultadosDTO;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa a tabela tb_benchmark no banco de dados.
 * A coluna 'results' é persistida diretamente como um tipo JSONB na
 * base de dados.
 */
@Data
@Entity
@NoArgsConstructor
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Table(name = "tb_benchmark")
public class Benchmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "benchmark_name", nullable = false, unique = true)
    private String benchmarkName;

    @Column(name = "first_country_name", nullable = false)
    private String firstCountryName;

    @Column(name = "last_country_name", nullable = false)
    private String lastCountryName;

    @Column(name = "first_date", nullable = false)
    private LocalDate firstDate;

    @Column(name = "last_date", nullable = false)
    private LocalDate lastDate;

    @Type(type = "jsonb")
    @Column(name = "results", columnDefinition = "jsonb")
    private ResultadosDTO results;

    public Benchmark(
            final String benchMarkName,
            final String firstCountryName,
            final String lastCountryName,
            final LocalDate firstDate,
            final LocalDate lastDate,
            final ResultadosDTO results

    ) {
        this.benchmarkName = benchMarkName;
        this.firstCountryName = firstCountryName;
        this.lastCountryName = lastCountryName;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.results = results;
    }

    public static Benchmark of(final String benchMarkName,
                               final String firstCountryName,
                               final String lastCountryName,
                               final String firstDate,
                               final String lastDate,
                               final ResultadosDTO results) {
        final Benchmark benchmark = new Benchmark(
                benchMarkName,
                firstCountryName,
                lastCountryName,
                LocalDate.parse(firstDate),
                LocalDate.parse(lastDate),
                results
        );

        return benchmark;
    }

}
