package com.foodordering.daoImp;

import com.foodordering.model.OrderDetail;
import com.foodordering.dao.OrderDetailDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("orderDetailDAO")
@Transactional
public class OrderDetailDaoImpl implements OrderDetailDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDetailDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public void add(OrderDetail orderDetail) {
        try {
            sessionFactory.getCurrentSession().save(orderDetail);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(int orderId) {
        Query query = sessionFactory
                .getCurrentSession()
                .createQuery("FROM OrderDetail od where od.orders.orderId=:orderId " , OrderDetail.class)
                .setParameter("orderId", orderId);
        return query.getResultList();
    }
   }
