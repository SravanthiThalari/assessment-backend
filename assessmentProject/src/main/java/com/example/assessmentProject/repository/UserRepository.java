
package com.example.assessmentProject.repository;

import com.example.assessmentProject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

}