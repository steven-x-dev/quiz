package com.twuc.shopping.domain;

import com.twuc.shopping.po.CartPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    private Long id;

    private long productId;

    private int quantity;

    public Cart(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(CartPO cartPO) {
        id = cartPO.getId();
        productId = cartPO.getProductId();
        quantity = cartPO.getQuantity();
    }

}
