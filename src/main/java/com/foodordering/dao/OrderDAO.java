package com.foodordering.dao;

import com.foodordering.model.Orders;
import com.foodordering.response.OrderListMapperResponseDto;

import java.util.Date;
import java.util.List;

public interface OrderDAO {
     Boolean add(Orders orders);
    List<OrderListMapperResponseDto> getOrderForAdminForToday(Date startDate, Date endDate, int userId);
    }
