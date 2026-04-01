package com.example.assessmentProject.Controller;

import com.example.assessmentProject.model.Question;
import com.example.assessmentProject.model.Submission;
import com.example.assessmentProject.repository.QuestionRepository;
import com.example.assessmentProject.repository.SubmissionRepository;
import com.example.assessmentProject.dto.SubmissionRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/submit")
@CrossOrigin("*")

public class SubmissionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SubmissionRepository submissionRepository;

    // ================= GET STUDENT RESULTS =================
    @GetMapping("/student/{studentId}")
    public List<Submission> getStudentResults(@PathVariable Long studentId){
        return submissionRepository.findByStudentId(studentId);
    }

    // ================= SUBMIT TEST =================
    @PostMapping("/{testId}")
    public ResponseEntity<?> submitTest(@PathVariable Long testId,
                                        @RequestBody SubmissionRequest request){

        Long studentId = request.getStudentId();
        Map<Long,String> answers = request.getAnswers();

        System.out.println("Student ID received: " + studentId);

        // 🚫 prevent multiple attempts
        Optional<Submission> existing =
                submissionRepository.findByStudentIdAndTestId(studentId,testId);

        if(existing.isPresent()){
            return ResponseEntity
                    .badRequest()
                    .body("You already attempted this test");
        }

        List<Question> questions = questionRepository.findByTestId(testId);

        int totalQuestions = questions.size();
        int score = 0;

        for(Question q : questions){

            String studentAnswer = answers.get(q.getId());

            if(q.getType() != null && q.getType().equals("CODING")){
                // simple coding evaluation
                if(studentAnswer != null && studentAnswer.contains(q.getExpectedOutput())){
                    score++;
                }
            }
            else {
                // MCQ
                if(studentAnswer != null && studentAnswer.equals(q.getCorrectAnswer())){
                    score++;
                }
            }
        }

        Submission submission = new Submission();

        submission.setStudentId(studentId);
        submission.setTestId(testId);
        submission.setScore(score);
        submission.setTotalQuestions(totalQuestions);          // ✅ FIXED
        submission.setSubmittedAt(LocalDateTime.now());        // ✅ FIXED

        Submission saved = submissionRepository.save(submission);

        return ResponseEntity.ok(saved);
    }

    // ================= CHECK ATTEMPT =================
    @GetMapping("/check/{studentId}/{testId}")
    public boolean hasAttempted(@PathVariable Long studentId,
                               @PathVariable Long testId){

        Optional<Submission> submission =
                submissionRepository.findByStudentIdAndTestId(studentId, testId);

        return submission.isPresent();
    }

    // ================= GET RESULT =================
    @GetMapping("/result/{studentId}/{testId}")
    public Submission getResult(@PathVariable Long studentId,
                               @PathVariable Long testId){

        return submissionRepository
               .findByStudentIdAndTestId(studentId, testId)
               .orElse(null);
    }

    // ================= ADMIN: ALL SUBMISSIONS =================
    @GetMapping("/all")
    public List<Submission> getAllSubmissions(){
        return submissionRepository.findAll();
    }
}