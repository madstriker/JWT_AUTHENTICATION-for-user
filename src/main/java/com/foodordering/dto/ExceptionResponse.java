package com.foodordering.dto;

public class ExceptionResponse {

    private String message;
    private String callerUrl;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCallerUrl() {
        return callerUrl;
    }

    public void setCallerUrl(String callerUrl) {
        this.callerUrl = callerUrl;
    }


}
