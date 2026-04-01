package com.example.assessmentProject.Controller;

import com.example.assessmentProject.jwt.JwtUtil;
import com.example.assessmentProject.model.Users;
import com.example.assessmentProject.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
private JwtUtil jwtUtil;
    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
public Object login(@RequestBody Users user){
     System.out.println("JWT UTIL: " + jwtUtil);

    Users dbUser = userService.findByEmail(user.getEmail());

    if(dbUser == null){
        return "User not found";
    }

    if(!dbUser.getPassword().equals(user.getPassword())){
        return "Invalid password";
    }

    // ✅ generate token
    String token = jwtUtil.generateToken(
            dbUser.getId(),
            dbUser.getEmail(),
            dbUser.getRole()
    );

    // ✅ return token + user
    return java.util.Map.of(
            "token", token,
            "user", dbUser
    );
}
    @GetMapping("/all")
    public List<Users> getUsers(){
        return userService.getAllUsers();
       
    }
}