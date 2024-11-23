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
    public ResponseEntity<?> getCategory(@PathVariable Integer categoryId){
        CategoryDto category = null;
        try {
            category = categoryService.getCategoryById(categoryId);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(Map.of("message", "Category Not Found"), HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto CategoryDto, @PathVariable Integer categoryId){
        CategoryDto updatedCategoryDto = null;
        try{
            updatedCategoryDto = categoryService.updateCategory(CategoryDto, categoryId);
        }
        catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(Map.of("message", "Category Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer categoryId){
        try{
            categoryService.deleteCategory(categoryId);
        }
        catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(Map.of("message", "Category Not Found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Map.of("message", "Category Deleted Successfully"), HttpStatus.OK);
    }

}
