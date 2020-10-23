package com.twuc.shopping.controller;

import com.twuc.shopping.domain.Cart;
import com.twuc.shopping.domain.Product;
import com.twuc.shopping.service.CartService;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartControllerTest {

    private final CartService cartService;

    @Autowired
    public CartControllerTest(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void should_list_cart_items() throws Exception {

        ResultActions resultActions = mockMvc
                .perform(get("/cartItems"))

                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        List<Cart> actual = new ArrayList<Cart>() {{
            add(new Cart(1L, 1L, 2));
        }};

        validateListCartResult(resultActions, actual);
    }

    void validateListCartResult(ResultActions resultActions, List<Cart> cartItems) throws Exception {
        for (int i = 0; i < cartItems.size(); i++) {
            Cart cartItem = cartItems.get(i);
            resultActions
                    .andExpect(jsonPath(String.format("$[%d].id"   , i), is(cartItem.getId()), Long.class))
                    .andExpect(jsonPath(String.format("$[%d].productId" , i), is(cartItem.getProductId()), Long.class))
                    .andExpect(jsonPath(String.format("$[%d].quantity", i), is(cartItem.getQuantity()), Integer.class));
        }
    }

}
