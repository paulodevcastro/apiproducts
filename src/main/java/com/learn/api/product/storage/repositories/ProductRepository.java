package com.learn.api.product.storage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.product.storage.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
