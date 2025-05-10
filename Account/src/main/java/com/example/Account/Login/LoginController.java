package com.example.Account.Login;

import com.example.Account.HashingUtil.HashingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    @ResponseBody
    public List<String> loginController(@RequestParam String usersUsername,
                                        @RequestParam String usersPassword) throws
            UnsupportedEncodingException {

        HashingUtil hashingUtil = new HashingUtil();

        usersPassword = hashingUtil.getHashPassword(usersPassword);

       return loginService.loginService(usersUsername,usersPassword);
    }

}
