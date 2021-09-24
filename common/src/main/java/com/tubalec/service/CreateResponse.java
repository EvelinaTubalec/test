package com.tubalec.service;

import com.tubalec.domain.Purchase;
import com.tubalec.domain.User;
import com.tubalec.domain.responses.PurchaseResponse;
import com.tubalec.domain.responses.RegistrationResponse;
import org.springframework.stereotype.Service;

@Service
public class CreateResponse {

    public PurchaseResponse transformToPurchaseResponse(Purchase purchase){
        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setId(purchase.getId());
        purchaseResponse.setBookId(purchase.getBookId());
        purchaseResponse.setDateOfBuying(purchase.getDateOfBuying());
        return purchaseResponse;
    }

    public RegistrationResponse transformToRegistrationResponse(User user){
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setName(user.getName());
        registrationResponse.setSurname(user.getSurname());
        registrationResponse.setAddress(user.getAddress());
        registrationResponse.setMoney(0.0f);
        registrationResponse.setTelephoneNumber(user.getTelephoneNumber());
        return registrationResponse;
    }

    public RegistrationResponse transformToInformationResponse(User user){
        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setName(user.getName());
        registrationResponse.setSurname(user.getSurname());
        registrationResponse.setAddress(user.getAddress());
        registrationResponse.setMoney(user.getMoney());
        registrationResponse.setTelephoneNumber(user.getTelephoneNumber());
        return registrationResponse;
    }
}
