package com.twuc.shopping.controller;

import com.twuc.shopping.domain.Product;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest {

    private static final String ROOT_URL = "/product";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_list_products_given_page_size_and_index() throws Exception {

        ResultActions resultActions = mockMvc.perform(get(ROOT_URL + "/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));

        List<Product> actual = new ArrayList<Product>() {{
            add(new Product(1L, "可乐", "瓶", 1));
        }};

        validateListProductResult(resultActions, actual);

    }

    void validateListProductResult(ResultActions resultActions, List<Product> products) throws Exception {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            resultActions
                    .andExpect(jsonPath(String.format("$[%d].id"   , i), is(product.getId()), Long.class))
                    .andExpect(jsonPath(String.format("$[%d].name", i), is(product.getName())))
                    .andExpect(jsonPath(String.format("$[%d].unit"  , i), is(product.getUnit())))
                    .andExpect(jsonPath(String.format("$[%d].price"  , i), is(product.getPrice()), Integer.class));
        }
    }

}
