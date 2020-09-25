package com.twuc.shopping.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Product {

    private Long id;

    public Product(@NotNull @Size(min = 1) String name, @NotNull @Size(min = 1) String unit, @Min(0) int price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    @NotNull
    @Size(min = 1)
    private String name;

    @NotNull
    @Size(min = 1)
    private String unit;

    @Min(0)
    private int price;

}
