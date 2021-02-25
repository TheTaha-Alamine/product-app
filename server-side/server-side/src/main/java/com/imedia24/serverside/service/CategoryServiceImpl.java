package com.imedia24.serverside.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imedia24.serverside.entity.Category;
import com.imedia24.serverside.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Transactional
	@Override
	public Page<Category> getAllCategories(Pageable page) {
		return categoryRepository.findAll(page);
	}
	@Transactional
	@Override
	public Optional<Category> getCategoryById(Long id) {
		return categoryRepository.findById(id);
	}
	@Transactional
	@Override
	public Category createCategory(String name) {
		Category category = new Category();
		category.setName(name);
		return categoryRepository.save(category);
	}
	@Transactional
	@Override
	public Category updateCategory(Category category, String name) {
		category.setName(name);
		return categoryRepository.save(category);
	}
	@Transactional
	@Override
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
		
	}

}
