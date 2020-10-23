package com.twuc.shopping.service;

import com.twuc.shopping.domain.Cart;
import com.twuc.shopping.po.CartPO;
import com.twuc.shopping.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAll() {
        List<CartPO> cartPOs = cartRepository.findAll();
        List<Cart> cartItems = new ArrayList<>();
        cartPOs.forEach(cartPO -> cartItems.add(new Cart(cartPO)));
        return cartItems;
    }
}
