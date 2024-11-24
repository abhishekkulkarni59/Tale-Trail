package com.code.taletrail.controller;

import com.code.taletrail.exception.ResourceNotFoundException;
import com.code.taletrail.payload.CategoryDto;
import com.code.taletrail.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //GET
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
        CategoryDto category = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategorys(){
        List<CategoryDto> categorys = categoryService.getAllCategories();
        return new ResponseEntity<>(categorys, HttpStatus.OK);
    }

    //POST
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto CategoryDto){
        CategoryDto createdCategoryDto = categoryService.createCategory(CategoryDto);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto CategoryDto, @PathVariable Integer categoryId){
        CategoryDto updatedCategoryDto = updatedCategoryDto = categoryService.updateCategory(CategoryDto, categoryId);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Map<String,String>> deleteCategory(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(Map.of("message", "Category Deleted Successfully"), HttpStatus.OK);
    }

}
