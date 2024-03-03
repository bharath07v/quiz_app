package com.example.quizapp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.quizapp.entity.QuestionEntity;
import com.example.quizapp.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuestionsControllerUnitTest {

	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	private QuestionService questionService;
	
	private QuestionsController questionsController;
	
//	
//	public QuestionsControllerUnitTest(MockMvc mockMvc, ObjectMapper objectMapper, QuestionService questionService,
//			QuestionsController questionsController) {
//		mockMvc = MockMvcBuilders.standaloneSetup(null);
//		this.objectMapper = objectMapper;
//	}
	
	@BeforeEach
	public void setup() {
		questionService = mock(QuestionService.class);
		questionsController = new QuestionsController(questionService);
		mockMvc = MockMvcBuilders.standaloneSetup(questionsController).build();
		objectMapper = new ObjectMapper();
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
