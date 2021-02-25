package com.imedia24.serverside.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imedia24.serverside.entity.Category;
import com.imedia24.serverside.entity.Product;

public interface ProductService {
	
	
	Page<Product> getAllProductsByCategoryId(Long categoryid, Pageable page);
	
	Optional<Product> getProductByIdAndByCategoryId(Long productid,Long categoryid);
	
	Product addProductToACategory(Long categoryid , String name, String currency, double price);
	
	Product updateProduct(Long productid,Long categoryid,String name, String currency, double price);
	
	void deleteProduct(Long productid, Long categoryid);
	
	boolean isProductInCategory(Product product,Category category);

}
