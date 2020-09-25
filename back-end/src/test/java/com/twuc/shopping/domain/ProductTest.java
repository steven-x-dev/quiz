package com.twuc.shopping.domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void should_create_product_without_id() {
        Product product = new Product("可乐", "瓶", 1);
        assertNull(product.getId());
        assertEquals(product.getName(), "可乐");
        assertEquals(product.getUnit(), "瓶");
        assertEquals(product.getPrice(), 1);
    }

    @Test
    void should_create_product_with_id() {
        Product product = new Product(1L, "可乐", "瓶", 1);
        assertEquals(product.getId(), 1L);
        assertEquals(product.getName(), "可乐");
        assertEquals(product.getUnit(), "瓶");
        assertEquals(product.getPrice(), 1);
    }

}
