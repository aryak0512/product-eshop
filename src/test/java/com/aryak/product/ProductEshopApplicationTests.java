package com.aryak.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductEshopApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	@DisplayName(value = "Check if context loaded correctly")
	void contextLoads() {
		assertThat(applicationContext.getApplicationName()).isEmpty();
	}

}
