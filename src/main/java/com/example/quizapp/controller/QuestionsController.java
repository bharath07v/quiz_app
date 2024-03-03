package com.example.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizapp.entity.QuestionEntity;
import com.example.quizapp.service.QuestionService;

@RestController
@RequestMapping("question/")
public class QuestionsController {

//	@Autowired
//	private QuestionService questionService;
	private final QuestionService questionService;

	public QuestionsController(QuestionService questionService) {
		this.questionService = questionService;
	}

	@GetMapping("allquestion")
	public List<QuestionEntity> getAllQuestions() {
		return questionService.getAllQuestions();
	}

	@GetMapping("{category}")
	public List<QuestionEntity> getAllQuestionsByCategory(@PathVariable("category") String category) {
		return questionService.getAllQuestionsByCategory(category);
	}

	@PostMapping("add")
	public ResponseEntity<Object> addQuestion(@RequestBody QuestionEntity question) {
		return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addQuestion(question));
	}

	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,
			@RequestParam Integer numQuestions) {
		return questionService.getQuestionsForQuiz(categoryName, numQuestions);
	}

}
