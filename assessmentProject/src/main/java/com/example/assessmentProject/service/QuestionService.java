package com.example.assessmentProject.service;

import com.example.assessmentProject.model.Question;
import com.example.assessmentProject.repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question addQuestion(Question question){
        return questionRepository.save(question);
    }

    public List<Question> getQuestionsByTest(Long testId){
        return questionRepository.findByTestId(testId);
    }

}