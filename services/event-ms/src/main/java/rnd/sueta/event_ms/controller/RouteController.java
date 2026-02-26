package rnd.sueta.event_ms.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import rnd.sueta.event_ms.dto.request.CreateRouteRq;
import rnd.sueta.event_ms.dto.response.RouteWithDetailsRs;
import rnd.sueta.event_ms.dto.response.RouteWithPlacesRs;

import java.util.UUID;

@RequestMapping("/api/v1/routes")
public interface RouteController {
    @GetMapping("/{id}")
    RouteWithPlacesRs getById(@PathVariable UUID id);

    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.CREATED)
    RouteWithDetailsRs generateRoute(@RequestBody @Valid CreateRouteRq createRouteRq);
}
