package com.foodordering.util;

import com.foodordering.model.OrderDetail;
import com.foodordering.response.OrderListResponseDto;
import com.foodordering.service.OrderDetailService;
import com.foodordering.response.FoodRes;
import com.foodordering.response.OrderListMapperResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderUtil {

    @Autowired
    private OrderDetailService orderDetailService;


    public OrderListResponseDto getOrderListMapperDtoList(OrderListMapperResponseDto orderListMapperDto) {
        OrderListResponseDto orderListDto = new OrderListResponseDto();
        List<FoodRes> foodResList = new ArrayList<>();
        orderListDto.setOrderId(orderListMapperDto.getOrderId());
        orderListDto.setUserId(orderListMapperDto.getUserId());
        orderListDto.setFirstName(orderListMapperDto.getFirstName());
        orderListDto.setMiddleName(orderListMapperDto.getMiddleName());
        orderListDto.setLastName(orderListMapperDto.getLastName());
        orderListDto.setOrderedDate(orderListMapperDto.getOrderedDate());
        orderListDto.setConfirm(orderListMapperDto.getConfirm());
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(orderListMapperDto.getOrderId());
        for (OrderDetail orderDetail : orderDetailList) {
            foodResList.add(FoodResUtil.addFoodRes(orderDetail));
            orderListDto.setFoodResList(foodResList);
        }
        return orderListDto;
    }

  /*  public UserListResponseDto getUserListDto(UserListMapperResponseDto userListMapperDto) {
        UserListResponseDto userListResponseDto = new UserListResponseDto();
        List<FoodRes> foodResList = new ArrayList<>();
        userListResponseDto.setUserId(userListMapperDto.getUserId());
        userListResponseDto.setOrderId(userListMapperDto.getOrderId());
        userListResponseDto.setOrderedDate(userListMapperDto.getOrderedDate());
        userListResponseDto.setConfirm(userListMapperDto.getConfirm());
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(userListMapperDto.getOrderId());
        for (OrderDetail orderDetail : orderDetailList) {
            foodResList.add(FoodResUtil.addFoodRes(orderDetail));
            userListResponseDto.setFoodResList(foodResList);
        }
        return userListResponseDto;
    }*/
}