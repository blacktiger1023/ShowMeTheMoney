package com.kakaopay.showmoney.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ApiController.class)
@AutoConfigureMockMvc
@SpringBootTest
class ApiControllerTest {
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	protected ObjectMapper mapper;
	
    @Test
    protected void creatItem() throws Exception {
    	RequestBuilder request = MockMvcRequestBuilders
				.post("/sprinkle")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"money\":10000,\"count\":10}")
				.contentType(MediaType.APPLICATION_JSON);
    }
}
