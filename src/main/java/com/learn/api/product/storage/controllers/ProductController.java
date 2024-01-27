package com.learn.api.product.storage.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.api.product.storage.entities.Product;
import com.learn.api.product.storage.exceptions.ProductNotFoundException;
import com.learn.api.product.storage.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll(){
		return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Product> getElementById(@PathVariable Long id){
		Optional<Product> product = productService.getProductById(id);
		if(!product.isPresent()) {
			ResponseEntity.status(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}
	
	@PostMapping(path = "/register")
	public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
		Product prod = productService.saveProduct(product);
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product updateProduct){
		return productService.getProductById(id)
				.map(product -> {
					product.setName(updateProduct.getName());
					product.setDescription(updateProduct.getDescription());
					product.setPrice(updateProduct.getPrice());
					product.setMoment(updateProduct.getMoment());
					Product updatedProduct = productService.saveProduct(product);
					return ResponseEntity.ok(updatedProduct);
				})
				.orElseThrow(() -> new ProductNotFoundException("Product not exist with id" + id));
	}
	
	@DeleteMapping("disable/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProductById(id);
	}
	
}
