package com.example.quizapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.quizapp.entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Integer>{

}
