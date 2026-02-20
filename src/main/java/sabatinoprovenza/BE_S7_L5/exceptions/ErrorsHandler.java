package sabatinoprovenza.BE_S7_L5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sabatinoprovenza.BE_S7_L5.payloads.ErrorsPayload;
import sabatinoprovenza.BE_S7_L5.payloads.ErrorsWithList;

@RestControllerAdvice
public class ErrorsHandler {


    @ExceptionHandler(ValidationExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithList handleValidationException(ValidationExceptions ex) {
        return new ErrorsWithList("Errore di validazione", ex.getErrorsList());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayload handleBadRequest(BadRequestException ex) {
        return new ErrorsPayload(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsPayload handleUnauthorized(UnauthorizedException ex) {
        return new ErrorsPayload(ex.getMessage());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorsPayload handleAuthorization(AuthorizationDeniedException ex) {
        return new ErrorsPayload(ex.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayload handleGeneric(Exception ex) {
        ex.printStackTrace();
        return new ErrorsPayload("Errore generico del server!");
    }
}