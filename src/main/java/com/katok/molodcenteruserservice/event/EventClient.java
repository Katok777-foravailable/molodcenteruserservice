package com.katok.molodcenteruserservice.event;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@FeignClient(name = "event-client", url = "${servers.molodcenter}" + "/api/events", dismiss404 = true)
public interface EventClient {
    @GetMapping
    Page<EventDto> getEvents(
            @RequestParam(required = false) OffsetDateTime startTimeFrom,
            @RequestParam(required = false) OffsetDateTime startTimeTo,
            @RequestParam(required = false) OffsetDateTime endTimeFrom,
            @RequestParam(required = false) OffsetDateTime endTimeTo,
            @RequestParam(defaultValue = "0") int page);

    @GetMapping("/{id}")
    ResponseEntity<EventDto> getEventById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<EventDto> addEvent(@Valid @RequestBody EventCreateDto eventCreateDtoDetails);

    @PatchMapping("/{id}")
    ResponseEntity<EventDto> updateEvent(@PathVariable Long id, @Valid @RequestBody EventCreateDto eventCreateDtoDetails);

    @DeleteMapping("/{id}")
    void deleteEvent(@PathVariable Long id);
}
