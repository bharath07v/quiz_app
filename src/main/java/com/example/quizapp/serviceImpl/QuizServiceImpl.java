package com.example.quizapp.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizapp.dto.QuestionWrapper;
import com.example.quizapp.dto.ResponseDTO;
import com.example.quizapp.entity.QuestionEntity;
import com.example.quizapp.entity.QuizEntity;
import com.example.quizapp.repo.QuestionRepository;
import com.example.quizapp.repo.QuizRepository;
import com.example.quizapp.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private QuizRepository quizRepository;

	@Override
	public ResponseEntity<String> createQuiz(String categoryName, String numQuestions, String title) {
		List<QuestionEntity> questions = questionRepository.findRandomQuestions(categoryName,
				Integer.parseInt(numQuestions));
//		List<QuestionEntity> questions = questionRepository.findByCategoryOrderByRand(categoryName).subList(0, Integer.parseInt(numQuestions));
		QuizEntity quizEntity = new QuizEntity();
		quizEntity.setQuestions(questions);
		quizEntity.setTitle(title);
		quizRepository.saveAndFlush(quizEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body("success");
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) { //get quiz question by quizId(primary key) and we returning only questions and options
		Optional<QuizEntity> quizop = quizRepository.findById(id);
		List<QuestionWrapper> questionWrappers = new ArrayList<>();
		if (quizop.isPresent()) {
			List<QuestionEntity> questions = quizop.get().getQuestions();
			for (QuestionEntity question : questions) {
				questionWrappers.add(new QuestionWrapper(question.getId(), question.getQuestionTitle(),
						question.getOption1(), question.getOption2(), question.getOption3(), question.getOptions4()));
			}
		}
		return ResponseEntity.ok(questionWrappers);
	}

	@Override
	public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseDTO> responses) {
		QuizEntity quiz = quizRepository.findById(id).get();
		List<QuestionEntity> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		for (ResponseDTO response : responses) {
			System.out.println(response.getResponse());
			System.out.println(questions.get(i).getRightAnswer());
			if (response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
	

//@Override
//public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseDTO> responses) {
//    QuizEntity quiz = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
//    List<QuestionEntity> questions = quiz.getQuestions();
//
//    long right = IntStream.range(0, Math.min(responses.size(), questions.size()))
//            .filter(i -> responses.get(i).getResponse().equals(questions.get(i).getRightAnswer()))
//            .count();
//
//    return ResponseEntity.ok(Math.toIntExact(right));
//}
	
//
//@Override
//public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseDTO> responses) {
//    QuizEntity quiz = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
//    List<QuestionEntity> questions = quiz.getQuestions();
//
//    long right = responses.stream()
//            .filter(response -> {
//                Optional<QuestionEntity> matchingQuestion = questions.stream()
//                        .filter(question -> question.getId().equals(response.getQuestionId()))
//                        .findFirst();
//                return matchingQuestion.isPresent() && response.getResponse().equals(matchingQuestion.get().getRightAnswer());
//            })
//            .count();
//
//    return ResponseEntity.ok(Math.toIntExact(right));
//}

}
