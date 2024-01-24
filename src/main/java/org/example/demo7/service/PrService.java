package org.example.demo7.service;

import org.example.demo7.model.Product;
import org.example.demo7.repository.PrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PrService implements IService{
    @Autowired
    PrRepository prRepository;
    @Override
    public void Create(Product product) {
        prRepository.save(product);

    }

    @Override
    public Optional<Product> FindById(int id) {
        return prRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        prRepository.deleteById(id);
    }

    @Override
    public void Update(int id, Product product) {
        product.setId(id);
        prRepository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return prRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findAllByName(Pageable pageable, String name) {
        return prRepository.findAllByNameContaining(pageable,name);
    }

}
