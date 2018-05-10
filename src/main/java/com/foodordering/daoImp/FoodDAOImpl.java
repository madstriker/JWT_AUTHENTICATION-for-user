package com.foodordering.daoImp;

import com.foodordering.dao.FoodDAO;
import com.foodordering.model.Food;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository("foodDAO")
@Transactional
public class FoodDAOImpl implements FoodDAO {

    @Autowired
    private SessionFactory sessionFactory;
    public List<Food> getAll() {
        return sessionFactory
                    .getCurrentSession()
                        .createQuery("FROM Food", Food.class)
                            .getResultList();
    }


    public void addFood(Food food) {
        sessionFactory.getCurrentSession().save(food);
    }

    public Food getFoodByName(String foodName) {
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery("FROM Food where name=:foodName", Food.class)
                    .setParameter("foodName", foodName).
                            getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException("foodName is not in the list");
        }
    }
}

