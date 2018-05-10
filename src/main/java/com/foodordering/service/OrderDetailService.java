package com.foodordering.service;


import com.foodordering.model.OrderDetail;


import java.util.List;

public interface OrderDetailService {


     List<OrderDetail> getOrderDetailByOrderId(int orderId);
}
