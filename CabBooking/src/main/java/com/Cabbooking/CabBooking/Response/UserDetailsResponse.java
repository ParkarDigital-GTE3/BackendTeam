package com.Cabbooking.CabBooking.Response;

import java.util.Date;

import com.Cabbooking.CabBooking.Model.User;



public class UserDetailsResponse {
    private Date timestamp;
    private String message;

    public UserDetailsResponse(Date timestamp, String message, String status, User result) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.result = result;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }

    private String status;
    private User result;
}
