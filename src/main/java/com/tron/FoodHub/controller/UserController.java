package com.tron.FoodHub.controller;

import com.tron.FoodHub.entity.User;
import com.tron.FoodHub.service.UserService;

import jakarta.servlet.http.HttpSession;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpSession session) {
        User user = userService.findByUsername(username);

        if (user != null && userService.checkPassword(password, user.getPassword())) {
        	//System.out.println(user.getPassword());
            // Store user information in session if needed
            session.setAttribute("user", user);
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
    	user.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
}
