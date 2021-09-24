package com.tubalec.controller;

import com.tubalec.domain.Purchase;
import com.tubalec.domain.User;
import com.tubalec.domain.requests.CreatePurchaseRequest;
import com.tubalec.domain.requests.GetPurchaseByIdRequest;
import com.tubalec.domain.responses.PurchaseResponse;
import com.tubalec.repository.BookDataRepository;
import com.tubalec.repository.PurchaseDataRepository;
import com.tubalec.repository.UserDataRepository;
import com.tubalec.service.CreateResponse;
import com.tubalec.util.PrincipalUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/rest/record")
@RequiredArgsConstructor
public class PurchaseController {

    private final PrincipalUtils principalUtils;

    private final CreateResponse transform;

    private final UserDataRepository userDataRepository;

    private final BookDataRepository bookDataRepository;

    private final PurchaseDataRepository purchaseDataRepository;

    @ApiOperation("Get all user's purchase")
    @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/purchase")
    public ResponseEntity<List<PurchaseResponse>> getAllUserPurchases(@ApiIgnore Principal principal) {
        String username = principalUtils.getUsername(principal);
        User userByLogin = userDataRepository.findByLogin(username);
        List<Purchase> userRecordsByUserId = purchaseDataRepository.findPurchaseByUserId(userByLogin.getId());
        List<PurchaseResponse> purchaseResponses = new ArrayList<>();

        for (Purchase purchase:userRecordsByUserId) {
            purchaseResponses.add(transform.transformToPurchaseResponse(purchase));
        }
        return new ResponseEntity<>(purchaseResponses,HttpStatus.OK);
    }

    @ApiOperation(value = "Get purchase information by purchase ID")
    @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    @PostMapping()
    public ResponseEntity<PurchaseResponse> getPurchaseById(@ApiIgnore Principal principal,
                                                            @RequestBody GetPurchaseByIdRequest createRequest) {
        String username = principalUtils.getUsername(principal);
        User userByLogin = userDataRepository.findByLogin(username);
        Purchase purchaseById = purchaseDataRepository.findPurchaseById(createRequest.getId(), userByLogin.getId());
        if(purchaseById != null ) {
            PurchaseResponse purchaseResponse = transform.transformToPurchaseResponse(purchaseById);
            return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
        }else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Purchase with id = " + createRequest.getId() + " doesn't exists");
    }

    @ApiOperation(value = "Save new purchase")
    @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    @PostMapping("/purchase")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PurchaseResponse> savePurchase(@ApiIgnore Principal principal,
                                                         @RequestBody CreatePurchaseRequest createRequest) {
        String username = principalUtils.getUsername(principal);
        User userByLogin;
        Float bookPriceByBookId;
        Float userMoneyByUserId;
        if(bookDataRepository.existsById(createRequest.getBookId())) {
            bookPriceByBookId = bookDataRepository.findBookPriceByBookId(createRequest.getBookId());
            userByLogin = userDataRepository.findByLogin(username);
            userMoneyByUserId = userDataRepository.findUserMoneyByUserId(userByLogin.getId());
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Book with id = " + createRequest.getBookId() + " doesn't exists");
        if (userMoneyByUserId >= bookPriceByBookId) {
            Purchase purchase = new Purchase();
            purchase.setUserId(userByLogin.getId());
            purchase.setBookId(createRequest.getBookId());
            purchase.setDateOfBuying(LocalDate.now());
            purchase.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
            purchase.setIsDeleted(false);
            Float userMoneyNow = userMoneyByUserId - bookPriceByBookId;
            userDataRepository.updateUser(userMoneyNow, userByLogin.getId());
            purchaseDataRepository.save(purchase);

            PurchaseResponse purchaseResponse = transform.transformToPurchaseResponse(purchase);
            return new ResponseEntity<>(purchaseResponse, HttpStatus.CREATED);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You have not money!Please top up the deposit!");
        }
    }

    @ApiOperation(value = "Delete purchase by purchase ID")
    @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    @DeleteMapping()
    public ResponseEntity<String> deletePurchase(@ApiIgnore Principal principal,
                                                 @Valid @RequestBody GetPurchaseByIdRequest createRequest) {
        String username = principalUtils.getUsername(principal);
        Long bookIdFromPurchase = purchaseDataRepository.findBookIdFromPurchase(createRequest.getId());
        Float bookPrice = bookDataRepository.findBookPriceByBookId(bookIdFromPurchase);
        Float userMoneyByUserId;
        User userByLogin;
        if(purchaseDataRepository.existsById(createRequest.getId())) {
            userByLogin = userDataRepository.findByLogin(username);
            userMoneyByUserId = userDataRepository.findUserMoneyByUserId(userByLogin.getId());
            Float userMoneyNow = userMoneyByUserId + bookPrice;
            userDataRepository.updateUser(userMoneyNow, userByLogin.getId());
            purchaseDataRepository.deleteById(createRequest.getId());
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Purchase with id = " + createRequest.getId() + " doesn't exists");
        return new ResponseEntity<>("You have successfully delete the purchase with id = "
                + createRequest.getId() + "!", HttpStatus.OK);
    }
}

