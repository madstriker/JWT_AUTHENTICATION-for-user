package com.foodordering.controller;

import com.foodordering.config.WebUrlConstant;
import com.foodordering.service.OrderDetailService;
import com.foodordering.dto.BillDto;
import com.foodordering.dto.OrderDto;
import com.foodordering.response.OrderListResponseDto;
import com.foodordering.service.OrdersService;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(WebUrlConstant.Order.ORDER)
public class OrdersController {

    private final OrdersService ordersService;
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrdersController(OrdersService ordersService, OrderDetailService orderDetailService){
        this.ordersService=ordersService;
        this.orderDetailService=orderDetailService;
    }

    @PostMapping
    public BillDto addOrder(@RequestBody OrderDto orderDto) {
        return ordersService.add(orderDto);
    }

    @GetMapping(WebUrlConstant.Order.TODAY_ORDER_TO_ADMIN)
    public ResponseEntity<List<OrderListResponseDto>> getCurrentDayOrderLogToAdmin
            (@RequestParam(value = "startDate",required = false) String startDate,
             @RequestParam(value="endDate",required = false) String endDate,
             @RequestParam(value="userId",required = false,defaultValue = "0")int userId) {

        if((startDate==null && endDate==null )|| userId==0){

            Date date = null;
            Date date1 = null;
            List<OrderListResponseDto> orderListResponsDtos = ordersService.getOrderForAdminForToday(date, date1,userId);
            return new ResponseEntity<>(orderListResponsDtos, HttpStatus.OK);
        }else if(startDate==null || endDate==null){
            throw new RuntimeException("either startDate or endDate is null");
        }
        else{
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
            LocalDate dt = formatter.parseLocalDate(startDate);
            LocalDate dt1 = formatter.parseLocalDate(endDate);
            if(dt.isAfter(dt1)){
                throw new RuntimeException("startdate should be greater than enddate");
            }
            Date date = dt.toDate();
            Date date1 = dt1.toDate();
            List<OrderListResponseDto> orderListResponsDtos = ordersService.getOrderForAdminForToday(date, date1,userId);
            return new ResponseEntity<>(orderListResponsDtos, HttpStatus.OK);
        }
    }
}
