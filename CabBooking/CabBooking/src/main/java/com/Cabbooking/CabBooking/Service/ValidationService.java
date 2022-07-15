package com.Cabbooking.CabBooking.Service;

import com.Cabbooking.CabBooking.Model.User;

public interface ValidationService {

    boolean emailValidation(User user);
    
    boolean emailValidationForVerification(String email);

}
