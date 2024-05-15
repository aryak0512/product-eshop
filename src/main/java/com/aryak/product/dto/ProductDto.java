package com.aryak.product.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ProductDto implements Serializable {

    private String name;
    private String description;
    private double price;

}
