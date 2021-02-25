package com.imedia24.serverside.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imedia24.serverside.entity.Category;
import com.imedia24.serverside.entity.Product;
import com.imedia24.serverside.exceptions.NotFoundException;
import com.imedia24.serverside.repository.CategoryRepository;
import com.imedia24.serverside.repository.ProductRepository;
import com.imedia24.serverside.util.CurrencyExchangeCommand;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
    private CurrencyExchangeCommand currencyExchangeCommand;
	
	@Transactional
	@Override
	public Page<Product> getAllProductsByCategoryId(Long categoryid, Pageable page) {
		Page<Product> products = productRepository.findProductsbyCategoryId(categoryid,page);
		return products;
		
	}
	
	@Transactional
	@Override
	public Optional<Product> getProductByIdAndByCategoryId(Long productid, Long categoryid) {
		return categoryRepository.findById(categoryid).map(p -> {
			return productRepository.findById(productid);
		}).orElseThrow(() -> new NotFoundException("category or product not found"));
	}
	
	@Transactional
	@Override
	public Product addProductToACategory(Long categoryid, String name, String currency, double price) {
		Category category = categoryRepository.findById(categoryid)
				.orElseThrow(()-> new NotFoundException("category not found"));
		
		if (!Product.CURRENCY.equals(currency)) {
            price = currencyExchangeCommand.convert(currency, Product.CURRENCY, price);
        }

        price = (double) Math.round(price * 100) / 100;
        
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setCategory(category);
		
		return productRepository.save(product);
		
	}

	@Transactional
	@Override
	public Product updateProduct(Long productid, Long categoryid, String name, String currency, double price) {
		Category category = categoryRepository.findById(categoryid)
				.orElseThrow(()-> new NotFoundException("category not found"));
		Product p = productRepository.findById(productid)
				.orElseThrow(()-> new NotFoundException("product not found"));
		
		if (!Product.CURRENCY.equals(currency)) {
            price = currencyExchangeCommand.convert(currency, Product.CURRENCY, price);
        }

        price = (double) Math.round(price * 100) / 100;
        
		p.setName(name);
		p.setPrice(price);
		p.setCategory(category);
		
		return productRepository.save(p);
	}

	@Transactional
	@Override
	public void deleteProduct(Long productid, Long categoryid) {
		Category category = categoryRepository.findById(categoryid)
				.orElseThrow(()-> new NotFoundException("category not found"));
		Product product = productRepository.findById(productid)
				.orElseThrow(()-> new NotFoundException("product not found"));
		
		productRepository.deleteById(productid);
		
	}

	@Override
	public boolean isProductInCategory(Product product, Category category) {
		return product.getCategory().getId().equals(category.getId());
	}

}
