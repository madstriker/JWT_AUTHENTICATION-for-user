package com.foodordering.service;

import com.foodordering.response.OrderListResponseDto;
import com.foodordering.dto.BillDto;
import com.foodordering.dto.OrderDto;

import java.util.Date;
import java.util.List;

public interface OrdersService {
    BillDto add(OrderDto orderDto);
    List<OrderListResponseDto> getOrderForAdminForToday(Date startDate, Date endDate, int userId);

}
