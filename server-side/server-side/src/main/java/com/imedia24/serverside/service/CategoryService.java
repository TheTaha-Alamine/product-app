package com.imedia24.serverside.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imedia24.serverside.entity.Category;

public interface CategoryService {
	
	Page<Category> getAllCategories(Pageable page);
	
	Optional<Category> getCategoryById(Long id);
	
	Category createCategory(String name);
	
	Category updateCategory(Category category,String name);
	
	void deleteCategory(Category category);
	
	
}
