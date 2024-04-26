package org.assignment.financialtradetool.exceptions.ex;

import org.assignment.financialtradetool.constants.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by sstefan
 * Date: 4/23/2024
 * Project: 01-backend
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = ErrorMessages.NOT_FOUND)
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    protected NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
