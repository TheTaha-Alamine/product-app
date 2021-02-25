package com.imedia24.serverside.controller;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imedia24.serverside.entity.Category;
import com.imedia24.serverside.exceptions.NotFoundException;
import com.imedia24.serverside.service.CategoryService;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	@GetMapping
	public ResponseEntity<?> readAllCategories(Pageable page){
		Page<Category> categories = categoryService.getAllCategories(page);
		
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> readACategory(@PathVariable Long id){
		Category category = categoryService.getCategoryById(id)
											.orElseThrow(()-> new NotFoundException("category not found try with another id"));
		
		return ResponseEntity.ok(category);
	}
	
	@PostMapping
	public ResponseEntity<?> createACategory(@RequestBody CategoryDto categoryd){
		Category category = categoryService.createCategory(categoryd.getName());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(category);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateACategory(@PathVariable Long id,@RequestBody CategoryDto categoryd){
		Category category = categoryService.getCategoryById(id)
							.orElseThrow(()-> new NotFoundException("category not found try with another id"));
		
		categoryService.updateCategory(category, categoryd.getName());
		
		return ResponseEntity.ok(category);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteACategory(@PathVariable Long id){
		Category category = categoryService.getCategoryById(id)
							.orElseThrow(()-> new NotFoundException("category not found try with another id"));
		
		categoryService.deleteCategory(category);
		
		return ResponseEntity.noContent().build();
	}
	
	static class CategoryDto{
		@NotNull
		@Size
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
		
	}
}
