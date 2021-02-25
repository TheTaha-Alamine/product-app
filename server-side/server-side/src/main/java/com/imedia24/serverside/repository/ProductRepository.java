package com.imedia24.serverside.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.imedia24.serverside.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	String GET_PRODUCTS_FROM_CATEGORY_ID = "SELECT p.* FROM product p WHERE p.category_id = ?1";
	
	@Query(value = GET_PRODUCTS_FROM_CATEGORY_ID, nativeQuery = true)
	Page<Product> findProductsbyCategoryId(Long categoryid,Pageable page);
	
	
}
