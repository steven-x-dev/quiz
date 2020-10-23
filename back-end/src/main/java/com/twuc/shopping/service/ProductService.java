package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.po.ProductPO;
import com.twuc.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        List<ProductPO> productPOs = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        productPOs.forEach(userPO -> products.add(new Product(userPO)));
        return products;
    }

    public Product findById(long id) {
        ProductPO productPO = productRepository.findById(id);
        if (productPO == null) {
            return null;
        } else {
            return new Product(productPO);
        }
    }

    public Long create(Product product) {

        ProductPO existing = productRepository.findByName(product.getName());

        if (existing != null) {
            return null;
        }

        ProductPO productPO = ProductPO.builder()
                .name(product.getName())
                .unit(product.getUnit())
                .price(product.getPrice())
                .url(product.getUrl())
                .build();
        productRepository.save(productPO);
        return productPO.getId();
    }

    public void deleteByName(String name) {
        productRepository.deleteByName(name);
    }

}
