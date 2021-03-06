package com.twuc.shopping.domain;

import com.twuc.shopping.po.ProductPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Size(min = 1)
    private String unit;

    @Min(0)
    private int price;

    @NotNull
    private String url;

    public Product(@NotNull @Size(min = 1) String name,
                   @NotNull @Size(min = 1) String unit,
                   @Min(0) int price,
                   @NotNull String url) {

        this.name = name;
        this.unit = unit;
        this.price = price;
        this.url = url;
    }

    public Product(ProductPO productPO) {
        id = productPO.getId();
        name = productPO.getName();
        unit = productPO.getUnit();
        price = productPO.getPrice();
        url = productPO.getUrl();
    }

}
