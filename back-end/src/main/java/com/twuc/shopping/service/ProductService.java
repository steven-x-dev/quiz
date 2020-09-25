package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();

    public List<Product> list(int pageSize, int pageIndex) {
        return products;
    }

    public Product findById(long id) {
        return products.stream().filter(p -> p.getId() == id).collect(Collectors.toList()).get(0);
    }

    public long create(Product product) {
        long id = products.size() + 1;
        product.setId(id);
        products.add(product);
        return id;
    }
}
