package com.AspasCabCustomer.Customer.Request;

public class UpdatePasswordRequest {
	
	String email;
    String newPassword;
    String oldPassword;

    public UpdatePasswordRequest(){
        super();
    }

    public UpdatePasswordRequest(String email, String newPassword, String oldPassword){
        this.email = email;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmailId(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

}
