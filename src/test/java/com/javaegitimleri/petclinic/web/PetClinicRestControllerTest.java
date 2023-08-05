package com.javaegitimleri.petclinic.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.javaegitimleri.petclinic.model.Owner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT) // Tomcat gömülü bir bicimde, uygulama icin tanımlı port üzerinden
@ActiveProfiles("dev")
public class PetClinicRestControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate =  restTemplate.withBasicAuth("user2", "secret");
	}

	@Test
	public void testGetOwnerById() {
		ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/owner/3", Owner.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getLastName(), Matchers.equalTo("Rize"));
	}

	@Test
	public void testGetOwnerByLastName() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Yücel",
				List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();
		List<String> names = body.stream().map(e -> e.get("name")).collect(Collectors.toList());
		MatcherAssert.assertThat(names, Matchers.containsInAnyOrder("Muammer", "Aslı"));
	}

	@Test
	public void testGetOwners() {
		ResponseEntity<List> response = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();
		List<String> names = body.stream().map(e -> e.get("name")).collect(Collectors.toList());
		MatcherAssert.assertThat(names, Matchers.containsInAnyOrder("Muammer", "Aslı", "Hümeyra", "Salim"));
	}
	
	@Test
	public void testCreateOwner() {
		Owner owner = new Owner();
		owner.setFirstName("Ali Ziya");
		owner.setLastName("Yücel");
		
		URI location = restTemplate.postForLocation("http://localhost:8080/rest/owner", owner);
		Owner owner2 = restTemplate.getForObject(location, Owner.class);
		
		MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo(owner2.getLastName()));
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo(owner2.getFirstName()));
		
		
	}
	
	@Test
	public void testUpdateOWner() {
		Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);
		owner.setFirstName("Salim Güray");
		restTemplate.put("http://localhost:8080/rest/owner/4", owner);
		
		owner = restTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);
		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Salim Güray"));
	}
	
	@Test
	public void testDeleteOWner() {
		//restTemplate.delete("http://localhost:8080/rest/owner/4");
		ResponseEntity<Void> exchange = restTemplate.exchange("http://localhost:8080/rest/owner/1", HttpMethod.DELETE, null, Void.class);

		try {
			restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);
			Assert.fail("Should have not returned owner");
		}catch(HttpClientErrorException ex) {
			MatcherAssert.assertThat(ex.getStatusCode().value(), Matchers.equalTo(404));
		}
	}
	
	@Ignore
	@Test
	public void testServiceLevelValidation() {
		
		Owner owner = new Owner();
	
		ResponseEntity<URI> responseEntity = restTemplate.postForEntity("http://localhost:8080/rest/owner", owner, URI.class);
		
		MatcherAssert.assertThat(responseEntity.getStatusCode(), Matchers.equalTo(HttpStatus.PRECONDITION_FAILED));
		
	}

}














