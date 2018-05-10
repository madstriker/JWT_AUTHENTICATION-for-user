package com.foodordering.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int orderId;
    @Column(name="confirm")
    private Boolean confirm;

    @Column(name="ordered_date")
    @Basic
    @Temporal(TemporalType.DATE)
    private Date orderedDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;



    public Boolean getConfirm() {
        return confirm;
    }
    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }
    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
