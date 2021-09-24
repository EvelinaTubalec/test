package com.tubalec.controller;

import com.tubalec.domain.Book;
import com.tubalec.domain.User;
import com.tubalec.domain.requests.IncreaseDepositRequest;
import com.tubalec.domain.responses.RegistrationResponse;
import com.tubalec.service.UserService;
import com.tubalec.repository.UserDataRepository;
import com.tubalec.service.CreateResponse;
import com.tubalec.util.PrincipalUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
@RequiredArgsConstructor
public class UserController {

    private final PrincipalUtils principalUtils;

    private final UserDataRepository userDataRepository;

    private final UserService userService;

    private final CreateResponse createResponse;

    @ApiOperation(value = "Get information about user")
    @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    @GetMapping()
    public ResponseEntity<RegistrationResponse> getInformationAboutUser(@ApiIgnore Principal principal) {
        String username = principalUtils.getUsername(principal);
        User userByLogin = userDataRepository.findByLogin(username);
        RegistrationResponse registrationResponse = createResponse.transformToInformationResponse(userByLogin);
        return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
    }

    @ApiOperation("Get user's books that he bought")
    @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    @GetMapping("/books")
    public List<Book> getBooksForAuthenticatedUser(@ApiIgnore Principal principal) {
        String username = principalUtils.getUsername(principal);
        User userByLogin = userDataRepository.findByLogin(username);
        return userService.getUserBooksById(userByLogin.getId());
    }

    @ApiOperation("Increase user's deposit")
    @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    @PutMapping("/deposit")
    public ResponseEntity<String> increaseDeposit(@ApiIgnore Principal principal,
                                @RequestBody IncreaseDepositRequest request) {
        String username = principalUtils.getUsername(principal);
        User byLogin = userDataRepository.findByLogin(username);
        if(request.getMoney()>300){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You can't increase you deposit more then 300 rubles. Please, try again!");
        }
        if(request.getMoney()<0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Bad request. Please, try again!");
        }
        Float actualDeposit = byLogin.getMoney() + request.getMoney();
        userDataRepository.updateUser(actualDeposit, byLogin.getId());
        return new ResponseEntity<>("You have successfully increased your deposit!", HttpStatus.OK);
    }
}
