package com.learn.api.product.storage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learn.api.product.storage.entities.Product;
import com.learn.api.product.storage.repositories.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Optional<Product> getProductById(Long id){
		return productRepository.findById(id);
	}
	
	public void validateNewProduct(Product product) {
		if(product.getName() == null || product.getName().isEmpty()) {
			throw new IllegalArgumentException("Product is void! Type a product name");
		}
	}
	
	public void priceBelowZero(Product product) {
		if(product.getPrice() < 0) {
			throw new IllegalArgumentException("Price below zero! Type a value high than zero");
		}
	}
	
	public Product saveProduct(Product product) {
		validateNewProduct(product);
		priceBelowZero(product);
		return productRepository.save(product);
	}
	
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}
}
