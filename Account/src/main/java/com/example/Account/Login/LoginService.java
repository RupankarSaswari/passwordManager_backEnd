package com.example.Account.Login;

import com.example.Account.JDBC.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginService {

    @Autowired
    LoginRepo loginRepo;
    @Autowired
    LoginModelClass loginModelClass;

    public List<String> loginService(String usersUsername, String usersPassword){

        List<String> loginDataList = new ArrayList<>();
       Boolean table = loginRepo.getData(usersUsername);

        if(table) {
            loginDataList.add("username = "+loginModelClass.getUsername());
            loginDataList.add("password = "+loginModelClass.getPassword());
            loginDataList.add("key = "+loginModelClass.getKey());
            loginDataList.add("ivparameterspec"+loginModelClass.getIvParameterSpec());
            if (usersPassword.equals(loginModelClass.getPassword())) {

                loginDataList.add("password matched");
                return loginDataList;

            } else {

                loginDataList.add("password not matched");
                return loginDataList;

            }
        }else {

            loginDataList.add("username is wrong");
            return loginDataList;

        }

    }
}
