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

    private Product product;

    private int quantity;

    public Cart(Long id, long productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Cart(CartPO cartPO) {
        id = cartPO.getId();
        productId = cartPO.getProductPO().getId();
        product = new Product(cartPO.getProductPO());
        quantity = cartPO.getQuantity();
    }

}
