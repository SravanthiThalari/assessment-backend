package com.example.assessmentProject.repository;

import com.example.assessmentProject.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Long> {
}