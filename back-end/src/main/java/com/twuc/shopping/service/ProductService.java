package com.twuc.shopping.service;

import com.twuc.shopping.domain.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> list(int pageSize, int pageIndex) {
        return new ArrayList<Product>() {{
            add(new Product(1L, "可乐", "瓶", 1));
        }};
    }

}
