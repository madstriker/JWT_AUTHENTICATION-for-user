package com.foodordering.dao;

import com.foodordering.model.User;

import java.util.List;

public interface UserDAO {

    User addUser(User user);
    List<User> getUsers();
    User getUser(int userId);
    User getUserByEmail(String userPassword,String email);
    User getUserByEmailId(String email);
    void update(User user);
    void updateUser(User user);

}
