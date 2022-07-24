package com.AspasCabCustomer.Customer.Response;

import java.util.Date;

import com.AspasCabCustomer.Customer.Model.Customer;





public class CustomerDetailsResponse {
    private Date timestamp;
    private String message;
    private String status;
    private Customer result;

    public CustomerDetailsResponse(Date timestamp, String message, String status, Customer result) {
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

    public Customer getResult() {
        return result;
    }

    public void setResult(Customer result) {
        this.result = result;
    }

}
