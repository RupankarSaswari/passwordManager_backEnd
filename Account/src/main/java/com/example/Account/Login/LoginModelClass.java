package com.example.Account.Login;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@Component
//@Scope("prototype")
public class LoginModelClass {

    private String username;
    private String password;
    private String key;
    private String ivParameterSpec;

    public String getIvParameterSpec() {
        return ivParameterSpec;
    }

    public void setIvParameterSpec(String ivParameterSpec) {
        this.ivParameterSpec = ivParameterSpec;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

}
