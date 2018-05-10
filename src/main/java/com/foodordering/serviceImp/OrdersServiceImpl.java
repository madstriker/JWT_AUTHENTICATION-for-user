package com.foodordering.serviceImp;

import com.foodordering.model.Food;
import com.foodordering.model.Orders;
import com.foodordering.model.User;
import com.foodordering.dao.FoodDAO;
import com.foodordering.dao.OrderDAO;
import com.foodordering.dao.OrderDetailDAO;
import com.foodordering.dao.UserDAO;
import com.foodordering.dto.BillDto;
import com.foodordering.dto.FoodQuantity;
import com.foodordering.dto.OrderDto;
import com.foodordering.exception.QuantityException;
import com.foodordering.exception.UserNotFoundException;
import com.foodordering.model.OrderDetail;
import com.foodordering.response.OrderListMapperResponseDto;
import com.foodordering.response.OrderListResponseDto;
import com.foodordering.service.OrdersService;
import com.foodordering.util.OrderUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrdersServiceImpl implements OrdersService {
    private final OrderUtil orderUtil;
    private final UserDAO userDAO;
    private final FoodDAO foodDAO;
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;

    @Autowired
    public OrdersServiceImpl(UserDAO userDAO, FoodDAO foodDAO,
                             OrderDAO orderDAO, OrderDetailDAO orderDetailDAO,OrderUtil orderUtil){
        this.userDAO=userDAO;
        this.foodDAO=foodDAO;
        this.orderDAO=orderDAO;
        this.orderDetailDAO=orderDetailDAO;
        this.orderUtil=orderUtil;
    }

    double balance;
    public BillDto add(OrderDto orderDto) {

        BillDto bal=new BillDto();
        List<Food> foodList=new ArrayList<Food>();
        User user=userDAO.getUser(orderDto.getUserId());
        if(user == null){
            throw new UserNotFoundException("user not found");
        }

        Orders orders = new Orders();
        orders.setUser(user);
        orders.setConfirm(false);
        orderDAO.add(orders);

        OrderDetail orderDetail = new OrderDetail();

        for(FoodQuantity foodQuantity : orderDto.getFoodList()) {

            orderDetail.setOrders(orders);
            orderDetail.setQuantity(foodQuantity.getQuantity());
            orderDetail.setFoodName(foodQuantity.getFoodName());
            orderDetail.setRestaurantName(foodQuantity.getRestaurantName());
            orderDetail.setQuantity(foodQuantity.getQuantity());
            orderDetail.setFoodPrice(foodQuantity.getFoodPrice());
            Food food=foodDAO.getFoodByName(foodQuantity.getFoodName());
            if(foodQuantity.getFoodPrice()!=food.getPrice()){
                throw new RuntimeException("food price is not in the list");
            }

            if(foodQuantity.getQuantity()<=0){
                    throw new QuantityException("quantity should be greater than 0");
                }
            foodList.add(food);
            int amount=foodQuantity.getQuantity()*foodQuantity.getFoodPrice();
            balance=user.getBalance()-amount;
            user.setBalance(balance);
            userDAO.update(user);
            bal.setBalance(balance);
            bal.setFoodList(foodList);
            orderDetailDAO.add(orderDetail);
        }
        return  bal;
        }

    @Override
    public List<OrderListResponseDto> getOrderForAdminForToday(Date startDate, Date endDate, int userId) {

        DateTime currentDate = DateTime.now();
        DateTime statDate = currentDate.minus(7);
        try {
            List<OrderListMapperResponseDto> orderListMapperDtoList = orderDAO.getOrderForAdminForToday(startDate,endDate,userId);
            List<OrderListResponseDto> orderListDtoList = new ArrayList<>();
            for (OrderListMapperResponseDto orderListMapperResponseDto : orderListMapperDtoList) {
                OrderListResponseDto orderListDto = orderUtil.getOrderListMapperDtoList(orderListMapperResponseDto);
                orderListDtoList.add(orderListDto);
            }
            return orderListDtoList;
        } catch (Exception e) {
            throw new RuntimeException("Cannot find order list" + e.getMessage());
        }
    }
}

