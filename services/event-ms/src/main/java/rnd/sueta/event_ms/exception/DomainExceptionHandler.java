package rnd.sueta.event_ms.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import rnd.sueta.event_ms.dto.response.ExceptionRs;
import rnd.sueta.event_ms.exception.custom.OutOfCityPointException;
import rnd.sueta.event_ms.util.ExceptionResponseFactory;

import java.time.Clock;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class DomainExceptionHandler {
    private final Clock clock;

    @ExceptionHandler(OutOfCityPointException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionRs handleOutOfCityPointException(OutOfCityPointException exception) {
        return ExceptionResponseFactory.newBadRequest(exception.getMessage(), clock);
    }
}
