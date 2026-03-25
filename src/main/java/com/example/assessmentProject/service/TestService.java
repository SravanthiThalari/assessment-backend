package com.example.assessmentProject.service;

import com.example.assessmentProject.model.Test;
import com.example.assessmentProject.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;

    public Test createTest(Test test){
        return testRepository.save(test);
    }
    public Test getTestById(Long id){
    return testRepository.findById(id).orElseThrow();
}

public void deleteTest(Long id){
    testRepository.deleteById(id);
}

    public List<Test> getAllTests(){
        return testRepository.findAll();
    }

}