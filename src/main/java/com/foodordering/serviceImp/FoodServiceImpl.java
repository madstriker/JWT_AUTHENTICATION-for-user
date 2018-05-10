package com.foodordering.serviceImp;

import com.foodordering.model.Food;
import com.foodordering.dao.FoodDAO;
import com.foodordering.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("foodService")
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodDAO foodDAO;

    public void addFood(Food food) {
        foodDAO.addFood(food);
    }

    public List<Food> getAll() {
        return foodDAO.getAll();
    }
}
