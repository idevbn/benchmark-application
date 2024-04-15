package com.backend.plnapi.exceptions;

import com.backend.plnapi.exceptionhandler.HttpException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Exceção disparada quando o nome de uma Benchmark não foi encontrado
 * na base de dados da aplicação.
 */
@Getter
public class BenchmarkNameNotFoundException extends RuntimeException implements HttpException {

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
        return new Object[0];
    }

}
