package com.example.quizapp.controller;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.quizapp.entity.QuestionEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class QuizAppApplicationINGTests {
	
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void addNumber() {
		assertEquals(2, 1+1);
	}
	
	@Test
	public void addQuestion() throws Exception {
		QuestionEntity questionEntity = new QuestionEntity();
		questionEntity.setCategory("java");
		questionEntity.setDifficultylevel("hard");
		questionEntity.setOption2("yes");
		questionEntity.setOption1("none");
		questionEntity.setOption3("half");
		questionEntity.setOptions4("no");
		questionEntity.setQuestionTitle("is java is complete oops oriented language");
		questionEntity.setRightAnswer("no");

		var response = mockMvc.perform(post("/question/add").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(questionEntity)));
		response.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(notNullValue())))
				.andExpect(jsonPath("$.category", is(questionEntity.getCategory())))
				.andExpect(jsonPath("$.difficultylevel", is(questionEntity.getDifficultylevel())))
				.andExpect(jsonPath("$.option1", is(questionEntity.getOption1())))
				.andExpect(jsonPath("$.option2", is(questionEntity.getOption2())))
				.andExpect(jsonPath("$.option3", is(questionEntity.getOption3())))
				.andExpect(jsonPath("$.options4", is(questionEntity.getOptions4())))
				.andExpect(jsonPath("$.questionTitle", is(questionEntity.getQuestionTitle())))
				.andExpect(jsonPath("$.rightAnswer", is(questionEntity.getRightAnswer())));
	}



}
