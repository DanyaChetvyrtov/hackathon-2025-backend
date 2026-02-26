package rnd.sueta.event_ms.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import rnd.sueta.event_ms.constants.ErrorMessages;
import rnd.sueta.event_ms.constants.PageConstants;
import rnd.sueta.event_ms.constants.RequestParamsValidationConstants;
import rnd.sueta.event_ms.dto.EventDto;
import rnd.sueta.event_ms.dto.EventWithPlaceDto;
import rnd.sueta.event_ms.dto.request.CreateEventRq;
import rnd.sueta.event_ms.dto.request.UpdateEventRq;
import rnd.sueta.event_ms.enums.EventType;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/events")
public interface EventController {
    @GetMapping
    Page<EventWithPlaceDto> getAll(
            @RequestParam(defaultValue = PageConstants.PAGE_DEFAULT_VALUE)
            @Min(value = PageConstants.PAGE_MIN_VALUE, message = ErrorMessages.NEGATIVE_PAGE_NUMBER)
            int page,
            @RequestParam(defaultValue = PageConstants.PAGE_SIZE_DEFAULT_VALUE)
            @Min(value = PageConstants.PAGE_SIZE_MIN_VALUE, message = ErrorMessages.INVALID_PAGE_SIZE)
            int size,
            @RequestParam(required = false)
            OffsetDateTime date,
            @RequestParam(required = false)
            @Size(max = RequestParamsValidationConstants.MAX_CATEGORIES_QUANTITY)
            List<EventType> categories
    );

    @GetMapping("/{id}")
    EventWithPlaceDto getById(@PathVariable UUID id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    EventDto create(@RequestBody @Valid CreateEventRq createEventRq);

    @PutMapping("/{id}")
    EventDto update(@PathVariable UUID id, @RequestBody @Valid UpdateEventRq updateEventRq);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id);
}
