package com.foodordering.controller;

import com.foodordering.model.Food;
import com.foodordering.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TOPSHI KREATS on 11/29/2017.
 */
@RestController
@RequestMapping("/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @PostMapping
    public void addFoods(@RequestBody Food food){
        foodService.addFood(food);
    }

    @GetMapping
    public List<Food> allFood(){
        return foodService.getAll();
    }
}