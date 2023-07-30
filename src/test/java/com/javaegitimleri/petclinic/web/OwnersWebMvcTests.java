package com.javaegitimleri.petclinic.web;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) // aslında default deger olarakda spring boot bunu kullanmakta WebEnvironment.MOCK
@ActiveProfiles("dev")
@AutoConfigureMockMvc   //bu anatasyon spring in mockmvc sınıfından bir nesneyi application.context te configure etmemizi saglıyor
@WithMockUser(username="user", password="secret", authorities = "USER")   //mockmvc requestlerinde authorication token icindeki kullanıcı bilgileri
public class OwnersWebMvcTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testOwners() throws Exception{
		
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/owners");
		ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
		MvcResult mvcResult = resultActions.andReturn();
		ModelAndView modelAndView = mvcResult.getModelAndView();
		
		MatcherAssert.assertThat(modelAndView.getViewName(), Matchers.equalTo("owners"));
		MatcherAssert.assertThat(modelAndView.getModel().containsKey("owners"), Matchers.is(true));
		
		
		
	}
}
