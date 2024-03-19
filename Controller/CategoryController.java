package com.example.ecommerce.Controller;

import com.example.ecommerce.Api.ApiResponse;
import com.example.ecommerce.Model.Category;
import com.example.ecommerce.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());

        categoryService.addCategory(category);
        return ResponseEntity.ok(new ApiResponse("Category added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable String id, @RequestBody @Valid Category category, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.badRequest().body(errors.getFieldError().getDefaultMessage());


        categoryService.updateCategory(category, id);
        return ResponseEntity.ok(new ApiResponse("Category updated successfully"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Category deleted successfully"));
    }
}
