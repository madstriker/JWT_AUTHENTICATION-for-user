package com.foodordering.dao;

import com.foodordering.model.OrderDetail;

import java.util.List;

public interface OrderDetailDAO {
     void add(OrderDetail orderDetail);
     List<OrderDetail> getOrderDetailByOrderId(int orderId);
}
