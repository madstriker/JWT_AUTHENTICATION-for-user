package com.foodordering.dao;

import com.foodordering.model.Food;

import java.util.List;

public interface FoodDAO {
    void addFood(Food food);
    List<Food> getAll();
    Food getFoodByName(String foodName);
}
