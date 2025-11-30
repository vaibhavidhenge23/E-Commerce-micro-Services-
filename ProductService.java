package com.productService.service;


import com.productService.Entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> getAllProduct();

    Product getProductById(Long id);

}
