package com.twuc.shopping.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.domain.Product;
import com.twuc.shopping.service.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.nio.charset.StandardCharsets;
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

    private final ProductService productService;

    @Autowired
    public ProductControllerTest(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void should_list_products_given_page_size_and_index() throws Exception {

        ResultActions resultActions = mockMvc
                .perform(get("/products"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Product> actual = new ArrayList<Product>() {{
            add(new Product(1L, "可乐", "瓶", 1, "https://images-na.ssl-images-amazon.com/images/I/71x5rVYbJUL._SL1500_.jpg"));
            add(new Product(2L, "雪碧", "听", 2, "https://images-na.ssl-images-amazon.com/images/I/41%2BrIHq5HtL.jpg"));
        }};

        validateListProductResult(resultActions, actual);
    }

    void validateListProductResult(ResultActions resultActions, List<Product> products) throws Exception {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            resultActions
                    .andExpect(jsonPath(String.format("$[%d].id"   , i), is(product.getId()), Long.class))
                    .andExpect(jsonPath(String.format("$[%d].name" , i), is(product.getName())))
                    .andExpect(jsonPath(String.format("$[%d].unit" , i), is(product.getUnit())))
                    .andExpect(jsonPath(String.format("$[%d].price", i), is(product.getPrice()), Integer.class));
        }
    }

    @Test
    @Order(2)
    void should_add_product_and_return_id_given_valid_params() throws Exception {

        productService.deleteByName("芬达");

        Product product = new Product("芬达", "瓶", 3, "https://images-na.ssl-images-amazon.com/images/I/71Cd1SW1pVL._SL1500_.jpg");
        String serialized = new ObjectMapper().writeValueAsString(product);

        mockMvc.perform(post("/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(serialized))

                .andExpect(status().isCreated())
                .andExpect(header().string("id", any(String.class)))
                .andDo(result -> {
                    String headerId = result.getResponse().getHeader("id");
                    if (headerId != null) {
                        long id = Long.parseLong(headerId);
                        Product fetchedProduct = productService.findById(id);
                        product.setId(id);
                        assertEquals(product, fetchedProduct);
                    } else {
                        fail();
                    }
                });
    }

    @Test
    @Order(3)
    void should_receive_400_with_msg_invalid_param_when_add_product_given_invalid_params() throws Exception {

        Product nullName = new Product(null, "听", 2, "https://images-na.ssl-images-amazon.com/images/I/41%2BrIHq5HtL.jpg");
        String serializedNullName = new ObjectMapper().writeValueAsString(nullName);

        mockMvc.perform(post("/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(serializedNullName))

                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is("invalid param")));

        Product nullUnit = new Product("雪碧", null, 2, "https://images-na.ssl-images-amazon.com/images/I/41%2BrIHq5HtL.jpg");
        String serializedNullUnit = new ObjectMapper().writeValueAsString(nullUnit);

        mockMvc.perform(post("/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(serializedNullUnit))

                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is("invalid param")));

        Product negativePrice = new Product("雪碧", "听", -1, "https://images-na.ssl-images-amazon.com/images/I/41%2BrIHq5HtL.jpg");
        String serializedNegativePrice = new ObjectMapper().writeValueAsString(negativePrice);

        mockMvc.perform(post("/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(serializedNegativePrice))

                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error", is("invalid param")));
    }

    @Test
    void should_receive_200_with_message_saying_existing_product_name_given_product_with_existing_name() throws Exception {

        Product product = new Product("雪碧", "瓶", 3, "https://images-na.ssl-images-amazon.com/images/I/71Cd1SW1pVL._SL1500_.jpg");
        String serialized = new ObjectMapper().writeValueAsString(product);

        mockMvc.perform(post("/product")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(serialized))

                .andExpect(status().isOk())
                .andExpect(header().string("message", "existing product name"));
    }

}
