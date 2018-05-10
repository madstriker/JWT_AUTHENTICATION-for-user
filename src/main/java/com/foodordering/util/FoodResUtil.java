package com.foodordering.util;

import com.foodordering.model.OrderDetail;
import com.foodordering.response.FoodRes;

public class FoodResUtil {

    public static FoodRes addFoodRes(OrderDetail orderDetail){
        FoodRes foodRes=new FoodRes();
        foodRes.setRestaurantName(orderDetail.getRestaurantName());
        foodRes.setQuantity(orderDetail.getQuantity());
        foodRes.setFoodPrice(orderDetail.getFoodPrice());
        foodRes.setFoodName(orderDetail.getFoodName());
        return foodRes;
    }
}
