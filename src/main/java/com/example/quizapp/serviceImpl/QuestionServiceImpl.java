package com.example.quizapp.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.entity.QuestionEntity;
import com.example.quizapp.repo.QuestionRepository;
import com.example.quizapp.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public List<QuestionEntity> getAllQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public List<QuestionEntity> getAllQuestionsByCategory(String category) {
		return questionRepository.findByCategory(category);
	}

	@Override
	public QuestionEntity addQuestion(QuestionEntity question) {
		QuestionEntity createdQuestion = questionRepository.saveAndFlush(question);
		return createdQuestion;
	}

	@Override
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
