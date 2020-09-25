package com.twuc.shopping.controller;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
