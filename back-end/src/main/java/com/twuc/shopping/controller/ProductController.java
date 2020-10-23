package com.twuc.shopping.controller;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> list() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/product/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {

        if (id < 0) throw new IllegalArgumentException("invalid product id");

        Product product = productService.findById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping(path = "/product")
    public ResponseEntity create(@RequestBody @Validated Product product) {

        Long productId = productService.create(product);

        if (productId == null) {
            return ResponseEntity.ok()
                    .header("message", "existing product name")
                    .build();
        } else {
            return ResponseEntity.created(null)
                    .header("id", Long.toString(productId))
                    .build();
        }
    }

}
