package com.backend.plnapi.exceptions;

import com.backend.plnapi.exceptionhandler.HttpException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exceção disparada quando uma benchmark não foi encontrado
 * na base de dados da aplicação.
 */
@Getter
public class BenchmarkNotFoundException extends RuntimeException implements HttpException {

    private final Long id;

    public BenchmarkNotFoundException(final Long id) {
        this.id = id;
    }

    @Override
    public String getErrorCode() {
        return "errors.benchmark.name.not-found";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public Object[] getErrorMessageParams() {
        return new String[]{this.getId().toString()};
    }

}
