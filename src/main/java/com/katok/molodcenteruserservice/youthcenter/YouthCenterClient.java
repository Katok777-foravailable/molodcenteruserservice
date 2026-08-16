package com.katok.molodcenteruserservice.youthcenter;

import com.katok.molodcenteruserservice.category.CategoryDto;
import com.katok.molodcenteruserservice.event.EventDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${servers.molodcenter}" + "/api/youth-centers")
public interface YouthCenterClient {
    @GetMapping("/{id}")
    YouthCenterDto getYouthCenterById(@PathVariable Long id);

    @GetMapping
    Page<YouthCenterDto> getYouthCentersByLocation(@RequestParam Double latitude,
                                                          @RequestParam Double longitude,
                                                          @RequestParam Double radius,
                                                          @RequestParam(defaultValue = "0") int page);

    @GetMapping("/{id}/events")
    Page<EventDto> getEventsByYouthCenter(@PathVariable Long id,
                                          @RequestParam(required = false) Long categoryId,
                                          @RequestParam(defaultValue = "0") int page);

    @GetMapping("/{id}/categories")
    Page<CategoryDto> getCategoriesByYouthCenter(@PathVariable Long id,
                                                 @RequestParam(defaultValue = "0") int page);

    @PostMapping
    ResponseEntity<YouthCenterDto> createYouthCenter(@Valid @RequestBody YouthCenterCreateDto youthCenterCreateDto);

    @PatchMapping("/{id}")
    YouthCenterDto updateYouthCenter(@PathVariable Long id, @Valid @RequestBody YouthCenterCreateDto youthCenterCreateDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteYouthCenter(@PathVariable Long id);
}
