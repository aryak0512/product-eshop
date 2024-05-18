package com.aryak.product;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@EnableEncryptableProperties
class ProductEshopApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	@DisplayName(value = "Check if context loaded correctly")
	void contextLoads() {
		assertThat(applicationContext.getApplicationName()).isEmpty();
	}

}
