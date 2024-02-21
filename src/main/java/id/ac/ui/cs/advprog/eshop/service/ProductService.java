package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);
    void edit(String targetId, Product editValues);
    void delete(String targetId);
    List<Product> findAll();
    Product findById(String targetId);
}
