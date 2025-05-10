package com.example.Account.AccountCreation;

import com.example.Account.JDBC.LoginRepo;
import com.example.Account.Json.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class AccountCreateController {

    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    private AccountCreateService accountCreateService;
    @PostMapping("/createAccount")
    @ResponseBody
    public String accountCreateController(@RequestParam String username,
                                          @RequestParam String masterPassword) throws
            NoSuchAlgorithmException, UnsupportedEncodingException {

        JsonConverter jsonConverter = new JsonConverter();

        boolean table = loginRepo.getTable(username);

        if(table){

            return jsonConverter.jsonConverter("Username is already taken");

        }else {
            table = loginRepo.homeTable(username);

            if (table) {
                //calling account creation service method
               if (accountCreateService.accountCreateService(username, masterPassword)){

                   return jsonConverter.jsonConverter("account creation success");

               }else {
                   loginRepo.dropTable(username+"info");

                   return jsonConverter.jsonConverter("Something went wrong try again");

               }

            }else {
                //deleting users info table
                loginRepo.dropTable(username+"info");

                return jsonConverter.jsonConverter("some thing went wrong please try again");

            }

        }

    }

}
