package com.backend.plnapi.controllers;

import com.backend.plnapi.dtos.in.BenchmarkInputDTO;
import com.backend.plnapi.infrastructure.IntegrationTestInitializer;
import com.backend.plnapi.models.Benchmark;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe na qual são realizados testes de integração referentes ao controller
 * {@link BenchmarkController}
 */
public class BenchmarkControllerTest extends IntegrationTestInitializer {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Método que testa a criação de uma {@link Benchmark}.
     * Como o retorno do endpoint é um status code 201 (CREATED) com
     * corpo vazio, foi adicionada uma query após a criação, utilizando
     * o entityManager, para comparar os valores de input na criação, com
     * valores encontrados no banco de dados.
     */
    @Test
    @Sql(scripts = "/database/clear_database.sql")
    public void shouldCreateABenchmark() {

        final String resourceLocation = "/benchmark";

        final BenchmarkInputDTO inputDTO = new BenchmarkInputDTO();
        inputDTO.setBenchmark("brazil-argentina");
        inputDTO.setFirstCountryName("brazil");
        inputDTO.setSecondCountryName("argentina");

        final HttpEntity<BenchmarkInputDTO> httpEntity = new HttpEntity<>(inputDTO);

        final ResponseEntity<Void> response = this.testRestTemplate.exchange(
                resourceLocation,
                HttpMethod.POST,
                httpEntity,
                Void.class
        );

        final TypedQuery<Benchmark> query = this.entityManager
                .createQuery("SELECT benchmark FROM Benchmark AS benchmark", Benchmark.class);

        final Benchmark benchmark = query.getResultList().get(0);

        final String benchmarkName = benchmark.getBenchmarkName();
        final String firstCountryName = benchmark.getFirstCountryName();
        final String lastCountryName = benchmark.getLastCountryName();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(benchmarkName, inputDTO.getBenchmark());
        assertEquals(firstCountryName.toLowerCase(), inputDTO.getFirstCountryName());
        assertEquals(lastCountryName.toLowerCase(), inputDTO.getSecondCountryName());
    }

}
