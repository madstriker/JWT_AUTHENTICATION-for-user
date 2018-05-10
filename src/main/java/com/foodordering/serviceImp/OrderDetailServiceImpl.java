package com.foodordering.serviceImp;

import com.foodordering.dao.OrderDetailDAO;
import com.foodordering.model.OrderDetail;
import com.foodordering.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO){
        this.orderDetailDAO=orderDetailDAO;
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        return orderDetailDAO.getOrderDetailByOrderId(orderId);
    }
}

