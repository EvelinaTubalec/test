package com.tubalec.controller;

import com.tubalec.domain.User;
import com.tubalec.domain.requests.UserRequestForRegistration;
import com.tubalec.domain.responses.RegistrationResponse;
import com.tubalec.repository.UserDataRepository;
import com.tubalec.service.CreateResponse;
import com.tubalec.service.CreateUser;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserDataRepository userDataRepository;

    private final CreateResponse create;

    private final CreateUser createUser;

    @ApiOperation(value = "Register new user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RegistrationResponse> createUser(@Valid @RequestBody UserRequestForRegistration createRequest) {
        String login = createRequest.getLogin();
        if(userDataRepository.findByLogin(login) != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed!User with this login already exists!");
        }
        User user = createUser.create(createRequest);
        userDataRepository.save(user);

        RegistrationResponse registrationResponse = create.transformToRegistrationResponse(user);
        return new ResponseEntity<>(registrationResponse, HttpStatus.CREATED);
    }
}
