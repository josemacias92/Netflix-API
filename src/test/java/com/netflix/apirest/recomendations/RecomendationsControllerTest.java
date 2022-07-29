package com.netflix.apirest.recomendations;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.apirest.entidades.Title;
import com.netflix.apirest.servicios.TitleService;

@WebMvcTest(RecomendationsController.class)
class RecomendationsControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TitleService titleService;
	
	
	@Test
	void getAllTitles() throws Exception {
		
		Title titleTest1 = new Title("1", "testName", "26/07/2022", "1992", "PG13", "100", "test test test", 5f, null, null, null);
		Title titleTest2 = new Title("2", "testName2", "26/07/2022", "2002", "TP", "80", "test test test", 2f, null, null, null);
		
		Mockito
		.when(titleService.getAll())
		.thenReturn(Arrays.asList(titleTest1, titleTest2));
		try {
		mockMvc
		.perform(MockMvcRequestBuilders.get("/api/titles"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("@.[1].id").value("1"))
		.andExpect(MockMvcResultMatchers.jsonPath("@.[1].name").value("testName2"));
		}catch(Exception e) {
			e.printStackTrace();
		}
					
	}
	
	@Test
	void getOneById() throws Exception {
		Title titleTest2 = new Title("2", "testName2", "26/07/2022", "2002", "TP", "80", "test test test", 2f, null, null, null);
		
		Mockito
		.when(titleService.getOne("2"))
		.thenReturn( titleTest2);
		try {
		mockMvc
		.perform(MockMvcRequestBuilders.get("/api/titles/2"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("@.id").value("2"))
		.andExpect(MockMvcResultMatchers.jsonPath("@.name").value("testName2"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
					
	}
	
	@Test
	@WithMockUser(username = "admin", password = "123", roles = { "USER" })
	void save() throws Exception {

		Title titleTest1 = new Title("1", "testName", "26/07/2022", "1992", "PG13", "100", "test test test", 5f, null, null, null);

		  ObjectMapper objectMapper = new ObjectMapper();
	        String json = objectMapper.writeValueAsString(titleTest1);
	        
		mockMvc.perform(MockMvcRequestBuilders.post("/api/titles")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.characterEncoding("utf-8"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	@WithMockUser(username = "admin", password = "1234", roles = { "USER" })
	void editTest() throws Exception {
		Title titleTest1 = new Title("1", "testName", "26/07/2022", "1992", "PG13", "100", "test test test", 5f, null, null, null);

		ObjectMapper objectMapper = new ObjectMapper();
	    String json = objectMapper.writeValueAsString(titleTest1);
	        
		mockMvc.perform(MockMvcRequestBuilders.put("/api/titles")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "123", roles = { "ADMIN" })
	void delete() throws Exception {
		
	        
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/titles/1")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("utf-8"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@WithMockUser(username = "admin", password = "123", roles = { "USER" })
	void deleteTest_withNonValidUser() throws Exception {
	        
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/titles/1"))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}
		
		
	}