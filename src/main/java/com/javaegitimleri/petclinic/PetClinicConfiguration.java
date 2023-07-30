package com.javaegitimleri.petclinic;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration
public class PetClinicConfiguration {

	
	@PostConstruct
	public void init() {
		System.out.println("\n");
		System.out.println("Hello World!..");
		System.out.println("by Diaz");
		System.out.println("\n");
		
		
	}
	
	
	
}
