package com.tubalec.service;

import com.tubalec.domain.User;
import com.tubalec.domain.requests.UserRequestForRegistration;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class CreateUser {

    public User create(UserRequestForRegistration createRequest){
        User user = new User();
        user.setName(createRequest.getName());
        user.setSurname(createRequest.getSurname());
        user.setAddress(createRequest.getAddress());
        user.setTelephoneNumber(createRequest.getTelephoneNumber());
        user.setLogin(createRequest.getLogin());
        user.setPassword(createRequest.getPassword());
        user.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        user.setMoney(0.0f);
        return user;
    }
}
