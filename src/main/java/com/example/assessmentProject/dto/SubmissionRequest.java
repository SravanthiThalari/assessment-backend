package com.example.assessmentProject.dto;
import com.example.assessmentProject.dto.SubmissionRequest;

import java.util.Map;

public class SubmissionRequest {

    private Long studentId;
    private Map<Long,String> answers;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Map<Long, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, String> answers) {
        this.answers = answers;
    }
}