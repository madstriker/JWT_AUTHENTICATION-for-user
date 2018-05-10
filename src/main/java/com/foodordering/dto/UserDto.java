package com.foodordering.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class UserDto {

    private int userId;
    private String userPassword;
    @NotBlank(message = "This field is required.")
    @Email
    private String email;
    @NotBlank(message = "This field is required.")
    private String address;
    @NotBlank(message = "This field is required.")
    @Size(min=2,max=20,message = "first name must be between 2 and 20.")
    private String firstName;
    @Size(min=1,max=20,message = "middle name must be between 1 and 20.")
    private String middleName;
    @Size(min=2,max=20,message = "last name must be between 2 and 20.")
    private String lastName;
    @NotBlank(message = "contact no is required.")
    private String contactNo;

    public UserDto() {
    }

    public UserDto(int userId, String userPassword, String email,
                   String address, String firstName, String middleName, String lastName, String contactNo) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.address = address;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contactNo = contactNo;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        return userId == userDto.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }
}
