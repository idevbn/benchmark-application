package com.backend.plnapi.exceptionhandler;

import com.backend.plnapi.exceptions.BenchmarkNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que manipula todas as exceções lançadas pela aplicação.
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({BenchmarkNotFoundException.class})
    public ResponseEntity<ApiError> handleBenchmarkNameNotFoundException(
            final BenchmarkNotFoundException ex
    ) {
        final String errorMsg = this.messageSource.getMessage(
                ex.getErrorCode(),
                ex.getErrorMessageParams(),
                LocaleContextHolder.getLocale()
        );

        final ApiError apiError = ApiError.of(
                ex.getHttpStatus(),
                errorMsg,
                Collections.singletonList(ex.toString())
        );

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request
    ) {
        final List<String> errors = new ArrayList<String>();

        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }

        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getDefaultMessage());
        }

        final ApiError apiError = ApiError
                .of(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

}
