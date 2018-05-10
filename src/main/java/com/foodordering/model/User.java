package com.foodordering.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="tbl_users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="middle_name",nullable = true)
    private String middleName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="user_password")
    private String userPassword;
    @Column(name="email")
    private String email;
    @Column(name="contact_no")
    private String contactNo;
    @Column(name="address")
    private String address;
    @Column(name="user_role")
    private String userRole;
    @Column(name="balance")
    private double balance;

    public User(String firstName, String middleName, String lastName, String userPassword,
                String email, String contactNo, String address, String userRole, double balance) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.userPassword = userPassword;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.userRole = userRole;
        this.balance = balance;
    }

    public User(String firstName, String middleName) {
        this.firstName = firstName;
        this.middleName = middleName;
    }

    public User(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                ", userRole='" + userRole + '\'' +
                ", balance=" + balance +
                '}';
    }
}
