package org.example.demo7.service;

import org.example.demo7.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IService {
    void Create(Product product);
    Optional<Product> FindById (int id);
    void delete(int id);
    void Update(int id,Product product);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByName(Pageable pageable, String name);

}
