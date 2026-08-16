package com.katok.molodcenteruserservice.category;

import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "${servers.molodcenter}" + "/api/categories")
public interface CategoryClient {
    @GetMapping
    Page<CategoryDto> getAllGlobalCategories(@RequestParam(defaultValue = "0") int page);

    @GetMapping("/{id}")
    CategoryDto getCategoryById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryCreateDto categoryCreateDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable Long id);

    @PatchMapping("/{id}")
    CategoryDto updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryCreateDto categoryCreateDto);
}
