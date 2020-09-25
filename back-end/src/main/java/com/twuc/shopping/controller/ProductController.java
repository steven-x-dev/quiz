package com.twuc.shopping.controller;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.service.ProductService;
import com.twuc.shopping.util.RequestUtil;
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

        int[] processedPagination = RequestUtil.processPagination(pageSize, pageIndex);
        List<Product> products = productService.list(processedPagination[0], processedPagination[1]);
        return ResponseEntity.ok(products);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {

        if (id < 0) throw new IllegalArgumentException("invalid product id");

        Product product = productService.findById(id);

        if (product == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody @Validated Product product) {

        long productId = productService.create(product);

        return ResponseEntity.created(null)
                .header("id", Long.toString(productId))
                .build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteById(@PathVariable long id) {
        if (id < 0) throw new IllegalArgumentException("invalid product id");
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
