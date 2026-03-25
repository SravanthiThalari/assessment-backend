package com.example.assessmentProject.Controller;

import com.example.assessmentProject.model.Question;
import com.example.assessmentProject.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin(origins="http://localhost:3000")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("/test/{testId}")
    public List<Question> getQuestions(@PathVariable Long testId){
        return questionService.getQuestionsByTest(testId);
    }

}