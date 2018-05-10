package com.foodordering.controller;

import com.foodordering.dto.UserDto;
import com.foodordering.model.User;
import com.foodordering.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.servlet.ServletException;
import javax.validation.Valid;
import java.awt.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User addUser(@RequestBody @Valid UserDto userDto)
    {
        User user=userService.addUser(userDto);
        return user;
    }

    //Example For RestTemplate
    @GetMapping("/sum/{num1}/{num2}")
    public int sum(@PathVariable int num1,@PathVariable int num2){
        return num1+num2;
    }

    //Example For ControllerTesing(TechPrimer)
    @GetMapping(value = "/hello",produces = MediaType.APPLICATION_JSON_VALUE)
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping(value = "/json")
    public User json(){
        return new User("saman","raj");
    }


    @PostMapping(value = "/verify")
    public String verifyUser(@RequestBody Map<String, String> json) throws ServletException{
        if(json.get("email")==null || json.get("userpassword")==null){
            throw new ServletException("please fill email and password");
        }

        String email=json.get("email");
        String userpassword=json.get("userpassword");
        User user=userService.getUserByEmailId(email);
        System.out.println("User: " + user);
        if(user==null){
            throw new ServletException("Invalid login. Please check your email and password");
        }

        String password=user.getUserPassword();
        if(!userpassword.equals(password)){
            throw new ServletException("Invalid login. Please check your password");
        }
        return Jwts.builder().setSubject(email)
                .claim("roles","user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretkey").compact();

    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        return userService.getUser(userId);
    }

}
