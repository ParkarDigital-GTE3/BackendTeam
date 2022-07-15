package com.Cabbooking.CabBooking.ServiceImpl;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Service.ValidationService;

import java.util.regex.Pattern;
@Service
public class ValidationServiceImpl implements ValidationService
{

    @Override
    public boolean emailValidation(User user)
    {
        
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (user.getEmail()!=null)
        {
            if(user.getEmail().matches(regexPattern))
            {
                return true;
            }
        }
        return false;
    }

	@Override
	public boolean emailValidationForVerification(String email) {
		// TODO Auto-generated method stub

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (email!=null)
        {
            if(email.matches(regexPattern))
            {
                return true;
            }
        }
		return false;
	}

//    @Override
//    public boolean accountNumberValidation(Account account)
//    {
//        long accNumber = account.getAccountNumber();
//        int leng = Long.toString(accNumber).length();
//        if(leng >= 12 && leng <= 14){
//            return true;
//        }
//        return false;
//    }
}
