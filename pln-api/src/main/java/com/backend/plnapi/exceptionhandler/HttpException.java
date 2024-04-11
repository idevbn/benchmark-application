package com.backend.plnapi.exceptionhandler;

import org.springframework.http.HttpStatus;

/**
 * Interface que define o contrato a ser seguido por exceções HTTP.
 */
public interface HttpException {

    String getErrorCode();

    HttpStatus getHttpStatus();

    Object[] getErrorMessageParams();

}
