package com.aryak.product.controller;

import com.aryak.product.dto.ProductDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @DynamicPropertySource
    public static void configureProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
        registry.add("spring.jpa.show-sql", () -> true);
    }

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @Test
    @DisplayName(value = "ADD product Integration test")
    @Order(1)
    void addProduct() throws Exception {

        ProductDto dto = ProductDto.builder()
                .description("Latest Iphone")
                .name("Iphone 15 pro")
                .price(267.00)
                .build();

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(dto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.name").value("Iphone 15 pro"));
    }

    @Test
    @DisplayName(value = "GET all products Integration test")
    @Order(2)
    void getAllProducts() throws Exception {

        mockMvc.perform(get("/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON) )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$.[0].name").value("Iphone 15 pro"));
    }

    @Test
    @DisplayName(value = "GET product by ID Integration test")
    @Order(3)
    void getProductById() throws Exception {
        long id = 1L;
        mockMvc.perform(get("/id/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.productId").value(id));
    }

    @Test
    @DisplayName(value = "GET product by name Integration test")
    @Order(4)
    void getProductByName() throws Exception {

        String name = "Iphone 15 pro";

        mockMvc.perform(get("/"+name)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").exists())
                .andExpect(jsonPath("$.[*].name").value(name));
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
