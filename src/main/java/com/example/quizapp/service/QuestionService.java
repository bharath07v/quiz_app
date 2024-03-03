package com.example.quizapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.quizapp.entity.QuestionEntity;

public interface QuestionService {
	
	List<QuestionEntity> getAllQuestions();
	
	List<QuestionEntity> getAllQuestionsByCategory(String category);

	QuestionEntity addQuestion(QuestionEntity question);

	ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions);

}
