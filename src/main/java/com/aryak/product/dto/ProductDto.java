package com.aryak.product.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductDto {

    private String name;
    private String description;
    private double price;

}
