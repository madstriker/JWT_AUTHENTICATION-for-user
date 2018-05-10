package com.foodordering.service;



import com.foodordering.model.Food;

import java.util.List;

/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */
public interface FoodService {
    List<Food> getAll();
    void addFood(Food food);
}
