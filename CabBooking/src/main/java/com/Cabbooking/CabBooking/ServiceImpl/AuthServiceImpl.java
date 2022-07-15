package com.Cabbooking.CabBooking.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Model.User;
import com.Cabbooking.CabBooking.Repository.UserRepository;
import com.Cabbooking.CabBooking.Service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;

//    @Autowired
//    AccountRepository accountRepository;
//
//    @Autowired
//    AccountController accountController;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public User createUser(User userDetails) {
        // TODO Auto-generated method stub

    	userDetails.setPassword(encoder.encode(userDetails.getPassword()));

        return userRepository.save(userDetails);
    }


    @Override
    public User fetchUserByEmail(String emailId) {
        // TODO Auto-generated method stub
        return userRepository.fetchUserByEmail(emailId);
    }

//    @Override
//    public void updatePassword(String emailId, String password) {
//
//        User user = userRepository.fetchUserByEmail(emailId);
//        user.setPassword(encoder.encode(password));
//        userRepository.save(user);
//
//    }

}
