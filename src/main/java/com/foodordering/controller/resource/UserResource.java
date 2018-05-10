package com.foodordering.controller.resource;

import com.foodordering.model.User;
import com.foodordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class UserResource {

    @Autowired
    UserService userService;

    @GetMapping("/user/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("/user/update")
    public User updateUser(@RequestBody User user){

        return  userService.updateUser(user);
    }
}
