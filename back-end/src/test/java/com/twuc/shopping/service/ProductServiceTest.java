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

    @Autowired
    public ProductServiceTest(ProductService productService) {
        this.productService = productService;
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
        long id = productService.create(product);
        product.setId(id);
        assertEquals(product, productService.findById(id));
    }

}
