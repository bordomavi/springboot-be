package com.javaegitimleri.petclinic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@Order(value=-1)  // @order ile configuration sınıflaında sıralamayı belirler diger sınıflarda @order yoksa ilk bu ayaga kalkar 
public class HalBrowserSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.antMatcher("/hal/***").authorizeRequests().anyRequest().permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
	
	}

	
}
