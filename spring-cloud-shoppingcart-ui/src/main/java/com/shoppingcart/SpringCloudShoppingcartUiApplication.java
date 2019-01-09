package com.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.shoppingcart.filters.AuthHeaderFilter;

@EnableZuulProxy
@SpringBootApplication
public class SpringCloudShoppingcartUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudShoppingcartUiApplication.class, args);
	}
	
	@Bean
    AuthHeaderFilter authHeaderFilter() {
        return new AuthHeaderFilter();
	}

}

