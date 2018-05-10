package com.foodordering.dto;

import com.foodordering.model.Food;


import java.util.List;

public class BillDto {

    private double balance;
    private List<Food> foodList=null;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }
}
