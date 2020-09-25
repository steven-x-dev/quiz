package com.twuc.shopping.controller;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Product>> list(@RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = false) Integer pageIndex) {

        return ResponseEntity.ok(productService.list(1, 0));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        return ResponseEntity.ok(productService.findById(1));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Validated Product product) {

        long productId = productService.create(product);

        return ResponseEntity.created(null)
                .header("id", Long.toString(productId))
                .build();
    }

}
