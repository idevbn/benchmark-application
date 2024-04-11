package com.backend.plnapi.exceptionhandler;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Classe que define a estrutura do corpo da resposta das mensagens de erro
 * disparadas pela aplicação.
 */
@Data
public class ApiError {

    private HttpStatus status;
    private String resume;
    private List<String> errors;

    public static ApiError of(
            final HttpStatus status,
            final String resume,
            final List<String> errors
    ) {
        final ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setResume(resume);
        apiError.setErrors(errors);

        return apiError;
    }

}
