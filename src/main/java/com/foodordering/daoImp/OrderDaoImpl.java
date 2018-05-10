package com.foodordering.daoImp;

import com.foodordering.dao.OrderDAO;
import com.foodordering.model.Orders;
import com.foodordering.response.OrderListMapperResponseDto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository("orderDAO")
@Transactional
public class OrderDaoImpl implements OrderDAO {

    private final SessionFactory sessionFactory;


    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    public Boolean add(Orders orders) {
        try {
            sessionFactory.getCurrentSession().save(orders);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<OrderListMapperResponseDto> getOrderForAdminForToday(Date startDate, Date endDate, int userId) {
        String queryString = "SELECT  new  OrderListMapperResponseDto " +
                "(o.orderId , o.user.userId, o.user.firstName," +
                "o.user.middleName,o.user.lastName,o.orderedDate,o.confirm)" +
                "FROM Orders o inner join User u on " +
                "o.user.userId=u.userId ";
        StringBuilder stringBuilder = new StringBuilder(queryString);
        if ((startDate == null || endDate == null) && userId == 0) {
            stringBuilder.append("and date(o.orderedDate)=current_date");
            return sessionFactory.getCurrentSession().createQuery(stringBuilder.toString()).getResultList();
        } else if ((startDate == null || endDate == null) && userId != 0) {
            stringBuilder.append("where o.user.userId=:userId and date(o.orderedDate)=current_date");
            return sessionFactory.getCurrentSession().createQuery(stringBuilder.toString())
                    .setParameter("userId", userId)
                    .getResultList();
        } else if ((startDate != null && endDate != null) && userId !=0) {
            stringBuilder.append("where o.user.userId=:userId and date(o.orderedDate)>=:startDate and" +
                    " date(o.orderedDate)<=:endDate");
            return sessionFactory.getCurrentSession().createQuery(stringBuilder.toString())
                    .setParameter("userId", userId)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        }else{
            stringBuilder.append("and date(o.orderedDate)>=:startDate and" +
                    " date(o.orderedDate)<=:endDate");
            return sessionFactory.getCurrentSession().createQuery(stringBuilder.toString())
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .getResultList();
        }
    }
}
