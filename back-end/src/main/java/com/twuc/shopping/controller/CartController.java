package com.twuc.shopping.controller;

import com.twuc.shopping.domain.Cart;
import com.twuc.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "/cartItems")
    public ResponseEntity<List<Cart>> list() {
        List<Cart> cartItems = cartService.findAll();
        return ResponseEntity.ok(cartItems);
    }

}
