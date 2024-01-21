package com.learn.api.product.storage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.api.product.storage.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
