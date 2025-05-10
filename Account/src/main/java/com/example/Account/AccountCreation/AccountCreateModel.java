package com.example.Account.AccountCreation;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
@Component
public class AccountCreateModel {

    private String username;
    private String password;
    private SecretKey secretKey;
    private IvParameterSpec ivParameterSpec;

    public IvParameterSpec getIvParameterSpec() {
        return ivParameterSpec;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIvParameterSpec(IvParameterSpec ivParameterSpec) {
        this.ivParameterSpec = ivParameterSpec;
    }
}
