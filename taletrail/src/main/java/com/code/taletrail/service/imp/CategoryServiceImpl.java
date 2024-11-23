package com.code.taletrail.service.imp;

import com.code.taletrail.exception.ResourceNotFoundException;
import com.code.taletrail.model.Category;
import com.code.taletrail.model.User;
import com.code.taletrail.payload.CategoryDto;
import com.code.taletrail.repository.CategoryRepo;
import com.code.taletrail.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category addedCategory = categoryRepo.save(category);
        return modelMapper.map(addedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", " Id ", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = categoryRepo.save(category);

        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", " Id ", categoryId));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map((category) -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", " Id ", categoryId));
        categoryRepo.delete(category);
    }
}
