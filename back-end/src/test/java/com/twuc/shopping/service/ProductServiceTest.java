package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
    void should_list_products() {
        List<Product> products = productService.findAll();
        assertEquals(new Product(1L, "可乐", "瓶", 1, "https://images-na.ssl-images-amazon.com/images/I/71x5rVYbJUL._SL1500_.jpg"), products.get(0));
        assertEquals(new Product(2L, "雪碧", "听", 2, "https://images-na.ssl-images-amazon.com/images/I/41%2BrIHq5HtL.jpg"), products.get(1));
    }

    @Test
    @Order(2)
    void should_find_product_given_id() {
        long id = 2;
        Product product = productService.findById(id);
        assertEquals(new Product(2L, "雪碧", "听", 2, "https://images-na.ssl-images-amazon.com/images/I/41%2BrIHq5HtL.jpg"), product);
    }

    @Test
    @Order(3)
    @Transactional
    void should_create_product() {
        productService.deleteByName("芬达");
        Product product = new Product("芬达", "瓶", 3, "https://images-na.ssl-images-amazon.com/images/I/71Cd1SW1pVL._SL1500_.jpg");
        long id = productService.create(product);
        product.setId(id);
        assertEquals(product, productService.findById(id));
    }

    @Test
    @Order(4)
    void should_return_null_given_product_with_existing_name() {
        Product product = new Product("雪碧", "瓶", 3, "https://images-na.ssl-images-amazon.com/images/I/71Cd1SW1pVL._SL1500_.jpg");
        Long id = productService.create(product);
        assertNull(id);
    }

}
