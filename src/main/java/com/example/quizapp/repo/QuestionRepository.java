package com.example.quizapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.quizapp.entity.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer>{

	List<QuestionEntity> findByCategory(String category);
	
//    List<QuestionEntity> findByCategoryOrderByRand(String category);

//	@Query(value = "FROM question_entity q Where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
//    List<QuestionEntity> findRandomQuestionsByCategorys(@Param("category") String category,@Param("numQ") int numQ);
    
    @Query(value = "SELECT * FROM question_entity q WHERE q.category = :category ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<QuestionEntity> findRandomQuestions(@Param("category") String category, @Param("limit") int numQ);
    
    

	
//    @Query(value = "SELECT q.id FROM question_entity q Where q.category=:category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
//    List<Integer> findRandomQuestionsByCategory(@Param("category") String category,@Param("numQ") int numQ);
}
