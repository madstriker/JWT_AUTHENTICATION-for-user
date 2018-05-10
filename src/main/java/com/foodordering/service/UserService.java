package com.foodordering.service;


import com.foodordering.dto.UserDto;
import com.foodordering.model.User;
import com.foodordering.dto.LoginDto;

import java.util.List;

public interface UserService {

    User addUser(UserDto userDto);
    List<User> getUsers();
    LoginDto verifyUser(String userPassword,String email);
    User getUserByEmailId(String email);
    User updateUser(User user);
    User getUser(int userId);
}
