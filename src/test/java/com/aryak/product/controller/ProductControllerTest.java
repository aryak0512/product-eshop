package com.aryak.product.controller;

import com.aryak.product.dto.ProductDto;
import com.aryak.product.model.Product;
import com.aryak.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp(){
        this.product = Product.builder()
                .price(0.45)
                .productId(565436L)
                .description("Brand new iphone launched")
                .name("Iphone 15")
                .build();
    }

    @Test
    @DisplayName(value = "GET all products test")
    void getAllProducts() throws Exception {

        when(productService.getAllProducts()).thenReturn(List.of(product));

        mockMvc.perform(get("/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[0].name").value("Iphone 15"));
    }

    @Test
    @DisplayName(value = "GET product by name test")
    void getProductByName() throws Exception {

        String name = "Iphone 15";
        when(productService.findProductByName(name)).thenReturn(List.of(product));

        mockMvc.perform(get("/" + name)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[0].name").value("Iphone 15"));

    }

    @Test
    @DisplayName(value = "GET product by ID test")
    void getProductById() throws Exception {

        var id = 565436;
        when(productService.findProductById(id)).thenReturn(product);

        mockMvc.perform(get("/id/" + id)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.productId").value(id));
    }

    @Test
    @DisplayName(value = "ADD product test")
    void addProduct() throws Exception {

        ProductDto dto = ProductDto.builder()
                .description("Latest Iphone")
                .name("Iphone 15 pro")
                .price(267.00)
                .build();

        when(productService.addProduct(dto)).thenReturn(dto);

        mockMvc.perform(post("/add")
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(asJsonString(dto))
                          .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.name").value("Iphone 15 pro"));
    }

    public static String asJsonString(final Object obj) {
        try {
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            return converter.getObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}