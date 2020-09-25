package com.twuc.shopping.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void should_create_product_without_id() {
        Product product = new Product("可乐", "瓶", 1, "https://images-na.ssl-images-amazon.com/images/I/71x5rVYbJUL._SL1500_.jpg");
        assertNull(product.getId());
        assertEquals(product.getName(), "可乐");
        assertEquals(product.getUnit(), "瓶");
        assertEquals(product.getPrice(), 1);
        assertEquals(product.getUrl(), "https://images-na.ssl-images-amazon.com/images/I/71x5rVYbJUL._SL1500_.jpg");
    }

    @Test
    void should_create_product_with_id() {
        Product product = new Product(1L, "可乐", "瓶", 1, "https://images-na.ssl-images-amazon.com/images/I/71x5rVYbJUL._SL1500_.jpg");
        assertEquals(product.getId(), 1L);
        assertEquals(product.getName(), "可乐");
        assertEquals(product.getUnit(), "瓶");
        assertEquals(product.getPrice(), 1);
        assertEquals(product.getUrl(), "https://images-na.ssl-images-amazon.com/images/I/71x5rVYbJUL._SL1500_.jpg");
    }

}
