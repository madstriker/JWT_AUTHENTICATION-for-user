package com.foodordering.daoImp;

import com.foodordering.exception.UserNotFoundException;
import com.foodordering.model.User;
import com.foodordering.dao.UserDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public User addUser(User user) {

        try {
            sessionFactory.getCurrentSession().save(user);
            if(user==null){
                throw new UserNotFoundException("user not found");
            }
            return user;
        } catch (Exception ex) {
            throw new RuntimeException("cannot find user"+ ex.getMessage());
        }
    }

    public List<User> getUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).getResultList();
    }

    public User getUser(int userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }

    public User getUserByEmail(String userPassword,String email) {
        try {
            User user1 = sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM User WHERE email=:email AND userPassword=:userPassword", User.class)
                    .setParameter("email",email)
                    .setParameter("userPassword",userPassword)
                    .getSingleResult();
            return user1;
        } catch (Exception ex) {
            return null;
        }
    }

    public User getUserByEmailId(String email) {

        try {

            User user1= sessionFactory.getCurrentSession().
                    createQuery("FROM User WHERE email=:email",User.class)
                    .setParameter("email",email).getSingleResult();
            return user1;
        } catch (Exception ex) {
            return null;
        }
    }

    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
