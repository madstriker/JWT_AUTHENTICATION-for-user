
package com.foodordering.test;

import com.foodordering.Application;
import com.foodordering.commons.HibernateTestConfig;
import com.foodordering.dao.UserDAO;
import com.foodordering.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {
        Application.class,
        HibernateTestConfig.class})

//@ActiveProfiles("test")
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    User u;

    @Before
    public void init(){
        u = new User();
        u.setFirstName("sumin");
        u.setLastName("Test");
        u.setUserRole("shakya");
        u.setAddress("test");
        u.setEmail("test@gmail.com");
        u.setBalance(23);
        u.setUserPassword("tst");
        u.setMiddleName("test");
        u.setContactNo("039483984");
    }

    @Test
    public void addUser_whenAdded_thenReturnOK(){
        User user = userDAO.addUser(u);
        User foundUser=userDAO.getUser(user.getUserId());
        Assert.assertEquals(foundUser,user);
    }

    @Test
    public void getUser_thenResultUserList(){
        List<User> userList=userDAO.getUsers();
        Assert.assertEquals(userDAO.getUsers(),userList);
    }
}

