package com.imedia24.serverside.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.imedia24.serverside.entity.Product;
import com.imedia24.serverside.exceptions.NotFoundException;
import com.imedia24.serverside.service.CategoryService;
import com.imedia24.serverside.service.ProductService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categories/{categoryid}/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity<?> readAllProductsByCategoryId(@PathVariable Long categoryid,Pageable page){
		Category category = categoryService.getCategoryById(categoryid)
				.orElseThrow(()-> new NotFoundException("category not found try with another id"));
		
		Page<Product> products = productService.getAllProductsByCategoryId(categoryid, page);
		
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{productid}")
	public ResponseEntity<?> readAProductByIdAndByCategoryId(@PathVariable Long categoryid,@PathVariable Long productid){
		Category category = categoryService.getCategoryById(categoryid)
				.orElseThrow(()-> new NotFoundException("category not found try with another id"));
		
		Product product = productService.getProductByIdAndByCategoryId(productid, categoryid)
				.orElseThrow(()-> new NotFoundException("product not found try with another id"));
		
		if(!productService.isProductInCategory(product, category)) {
			throw new IllegalArgumentException(
					"category with id : "+categoryid+" does not contain product with id : "+productid);
		}
		
		
		
		return ResponseEntity.ok(product);
	}
	
	@PostMapping
	public ResponseEntity<?> createAProductInACategory(@PathVariable Long categoryid,@RequestBody @Valid ProductDto productD){
		Category category = categoryService.getCategoryById(categoryid)
				.orElseThrow(()-> new NotFoundException("category not found try with another id"));
		
		
		Product product = productService.addProductToACategory(categoryid, productD.getName(), productD.getCurrency(), productD.getPrice());
		
		return ResponseEntity.ok(product);
	}
	
	@PutMapping("/{productid}")
	public ResponseEntity<?> updateAProductInACategory(@PathVariable Long categoryid,@PathVariable Long productid,@RequestBody ProductDto productD){
		Category category = categoryService.getCategoryById(categoryid)
				.orElseThrow(()-> new NotFoundException("category not found"));
		Product product = productService.getProductByIdAndByCategoryId(productid, categoryid)
				.orElseThrow(()-> new NotFoundException("product not found"));
		if(!productService.isProductInCategory(product, category)) {
			throw new IllegalArgumentException(
					"category with id : "+categoryid+" does not contain product with id : "+productid);
		}
		
		
		
		product = productService.updateProduct(productid, categoryid, productD.getName(), productD.getCurrency(), productD.getPrice());
		
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/{productid}")
	public ResponseEntity<?> deleteAProductFromACategory(@PathVariable Long categoryid,@PathVariable Long productid){
		Category category = categoryService.getCategoryById(categoryid)
				.orElseThrow(()-> new NotFoundException("category not found"));
		Product product = productService.getProductByIdAndByCategoryId(productid, categoryid)
				.orElseThrow(()-> new NotFoundException("product not found"));
		if(!productService.isProductInCategory(product, category)) {
			throw new IllegalArgumentException(
					"category with id : "+categoryid+" does not contain product with id : "+productid);
		}
		
		
		productService.deleteProduct(productid, categoryid);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	static class ProductDto {
        @NotNull(message = "name is required")
        @Size(message = "name must be equal to or lower than 300", min = 1, max = 300)
        private String name;
        @NotNull
        @Size(message = "Currency must be in ISO 4217 format", min = 3, max = 3)
        private String currency;
        @NotNull(message = "name is required")
        @Min(0)
        private Double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
	
	
	
}
