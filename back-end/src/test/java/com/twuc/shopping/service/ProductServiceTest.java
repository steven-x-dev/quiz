package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import com.twuc.shopping.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTest {

    private final ProductService productService;

    private final ProductRepository productRepository;

    private static long createdProductId;

    @Autowired
    public ProductServiceTest(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Test
    @Order(1)
    void should_list_products_given_pagination_setting() {
        int pageSize = 2;
        int pageIndex = 0;
        List<Product> products = productService.list(pageSize, pageIndex);
        assertEquals(new Product(1L, "可乐", "瓶", 1), products.get(0));
        assertEquals(new Product(2L, "雪碧", "听", 2), products.get(1));
    }

    @Test
    @Order(2)
    void should_find_product_given_id() {
        long id = 2;
        Product product = productService.findById(id);
        assertEquals(new Product(2L, "雪碧", "听", 2), product);
    }

    @Test
    @Order(3)
    void should_create_product() {
        Product product = new Product("芬达", "瓶", 3);
        createdProductId = productService.create(product);
        product.setId(createdProductId);
        assertEquals(product, productService.findById(createdProductId));
    }

    @Test
    @Order(4)
    void should_delete_product_given_id() {

        Product existing = productService.findById(createdProductId);
        if (existing == null) {
            should_create_product();
        }

        long countBefore = productRepository.count();

        productService.deleteById(createdProductId);

        long countAfter = productRepository.count();
        assertEquals(countBefore, countAfter + 1);
    }

}
