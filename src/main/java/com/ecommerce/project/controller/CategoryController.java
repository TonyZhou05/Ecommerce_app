package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
    @PostMapping("/admin/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {

        categoryService.createCategory(category);
        return new ResponseEntity<>("Category created Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        String status = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> createCategory(@RequestBody Category category, @PathVariable Long categoryId) {
        Category savedCategory = categoryService.updateCategory(category, categoryId);
        return new ResponseEntity<>("Updated category with category id: " + categoryId, HttpStatus.OK);

    }

}