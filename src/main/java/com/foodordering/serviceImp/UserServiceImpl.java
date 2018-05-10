package com.foodordering.serviceImp;

import com.foodordering.dto.UserDto;
import com.foodordering.model.User;
import com.foodordering.dao.UserDAO;
import com.foodordering.dto.LoginDto;
import com.foodordering.exception.UnauthorizedExceptionHandler;
import com.foodordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    

    public User addUser(UserDto userDto) {

        User user1 = userDAO.getUserByEmailId(userDto.getEmail());
          if (user1 == null) {
            User user=new User();
            user.setFirstName(userDto.getFirstName());
            user.setMiddleName(userDto.getMiddleName());
            user.setLastName(userDto.getLastName());
            user.setContactNo(userDto.getContactNo());
            user.setUserPassword(userDto.getUserPassword());
            user.setAddress(userDto.getAddress());
            user.setEmail(userDto.getEmail());
            user.setBalance(1200);
            user.setUserRole("user");
            userDAO.addUser(user);
            return user;
        }else{
            throw new IllegalArgumentException("plz rewite email");
        }
    }

    public List<User> getUsers() {
        return userDAO.getUsers();
    }

    public LoginDto verifyUser(String userPassword, String email) {

        User user1 = userDAO.getUserByEmail(userPassword,email);
        if(user1==null){
            throw new UnauthorizedExceptionHandler("Invalid user name or password.");
        }
        System.out.println(user1.getAddress());
        LoginDto loginDto1 = new LoginDto();
        loginDto1.setEmail(user1.getEmail());
        loginDto1.setId(user1.getUserId());
        loginDto1.setFirstName(user1.getFirstName());
        loginDto1.setMiddleName(user1.getMiddleName());
        loginDto1.setLastName(user1.getLastName());
        loginDto1.setContactNo(user1.getContactNo());
        loginDto1.setEmail(user1.getEmail());
        loginDto1.setAddress(user1.getAddress());
        loginDto1.setUserRole(user1.getUserRole());
        loginDto1.setBalance(user1.getBalance());
        return loginDto1;
    }

    @Override
    public User getUserByEmailId(String email) {
      return   userDAO.getUserByEmailId(email);
    }

    @Override
    public User updateUser(User user) {
        userDAO.updateUser(user);
        return user;
    }

    @Override
    public User getUser(int userId) {
        return userDAO.getUser(userId);
    }
}
