package com.example.assessmentProject.Controller;

import com.example.assessmentProject.model.Test;
import com.example.assessmentProject.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/create")
    public Test createTest(@RequestBody Test test){
        return testService.createTest(test);
    }
    @PutMapping("/update/{id}")
public Test updateTest(@PathVariable Long id, @RequestBody Test updatedTest){

    Test test = testService.getTestById(id);

    test.setTitle(updatedTest.getTitle());
    test.setDuration(updatedTest.getDuration());

    return testService.createTest(test); // reuse save
}

@DeleteMapping("/delete/{id}")
public String deleteTest(@PathVariable Long id){

    testService.deleteTest(id);
    return "Test deleted successfully";
}

    @GetMapping("/all")
    public List<Test> getTests(){
        return testService.getAllTests();
    }

}