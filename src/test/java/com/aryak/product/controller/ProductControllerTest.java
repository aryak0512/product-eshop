package com.aryak.product.controller;

import com.aryak.product.model.Product;
import com.aryak.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getAllProducts() throws Exception {

        var brandNewIphoneLaunched = Product.builder()
                .price(0.45)
                .productId(565436L)
                .description("Brand new iphone launched")
                .name("Iphone 15")
                .build();

        when(productService.getAllProducts()).thenReturn(List.of(brandNewIphoneLaunched));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void getProductByName() throws Exception {

        String name = "Iphone 15";
        var brandNewIphoneLaunched = Product.builder()
                .price(0.45)
                .productId(565436L)
                .description("Brand new iphone launched")
                .name("Iphone 15")
                .build();

        when(productService.findProductByName(name)).thenReturn(List.of(brandNewIphoneLaunched));
        // the below assertion is for service layer, not controller!
        //assertEquals(productService.findProductByName(name).size(),1, "Incorrect number of products returned!");

        mockMvc.perform(get("/" + name))
                .andExpect(status().isOk());
    }

    @Test
    void getProductById() throws Exception {

        long id = 100;
        var brandNewIphoneLaunched = Product.builder()
                .price(0.45)
                .productId(565436L)
                .description("Brand new iphone launched")
                .name("Iphone 15")
                .build();

        when(productService.findProductById(id)).thenReturn(brandNewIphoneLaunched);
        mockMvc.perform(get("/id/" + id))
                .andExpect(status().isOk());
    }

}