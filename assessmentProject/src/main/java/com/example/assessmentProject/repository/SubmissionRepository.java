package com.example.assessmentProject.repository;

import com.example.assessmentProject.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByStudentId(Long studentId);
    Optional<Submission> findByStudentIdAndTestId(Long studentId, Long testId);

}