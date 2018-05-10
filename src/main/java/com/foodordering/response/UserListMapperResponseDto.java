package com.foodordering.response;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class UserListMapperResponseDto {

    private int orderId;
    private int userId;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderedDate;
    private Boolean confirm;

    public UserListMapperResponseDto(int orderId, int userId, Date orderedDate, Boolean confirm) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderedDate = orderedDate;
        this.confirm = confirm;
    }

    public UserListMapperResponseDto() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Boolean getConfirm() {
        return confirm;
    }

    public void setConfirm(Boolean confirm) {
        this.confirm = confirm;
    }
}
