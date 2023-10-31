package dev.wsalquinga.vehicles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * @author wsalquinga on 31/10/2023
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ServerErrorException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ServerErrorException(String message) {
        super(message);
    }
}
