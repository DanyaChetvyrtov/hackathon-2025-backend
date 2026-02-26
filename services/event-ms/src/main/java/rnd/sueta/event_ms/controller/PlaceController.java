package rnd.sueta.event_ms.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
import rnd.sueta.event_ms.dto.PlaceDto;
import rnd.sueta.event_ms.dto.request.CreatePlaceRq;
import rnd.sueta.event_ms.dto.request.UpdatePlaceRq;
import rnd.sueta.event_ms.dto.response.GetPlacesRs;

import java.util.UUID;

@RequestMapping("/api/v1/places")
public interface PlaceController {
    @GetMapping
    GetPlacesRs getAll(
            @RequestParam(defaultValue = PageConstants.PAGE_DEFAULT_VALUE)
            @Min(value = PageConstants.PAGE_MIN_VALUE, message = ErrorMessages.NEGATIVE_PAGE_NUMBER)
            int page,
            @RequestParam(defaultValue = PageConstants.PAGE_SIZE_DEFAULT_VALUE)
            @Min(value = PageConstants.PAGE_SIZE_MIN_VALUE, message = ErrorMessages.INVALID_PAGE_SIZE)
            int size
    );

    @GetMapping("/{eventId}")
    PlaceDto getById(@PathVariable UUID id);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PlaceDto create(@RequestBody @Valid CreatePlaceRq createPlaceRq);

    @PutMapping("/{eventId}")
    PlaceDto update(@PathVariable UUID id, @RequestBody @Valid UpdatePlaceRq updatePlaceRq);

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable UUID id);
}
