package com.example.Account.AccountCreation;

import com.example.Account.Encryption.EncryptionComponent;
import com.example.Account.HashingUtil.HashingUtil;
import com.example.Account.JDBC.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Component
public class AccountCreateService {

    @Autowired
    private AccountCreateModel accountCreateModel;
    @Autowired
    private LoginRepo loginRepo;

    public Boolean accountCreateService(String username, String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {

        EncryptionComponent encryptionComponent = new EncryptionComponent();
        HashingUtil hashingUtil = new HashingUtil();

        accountCreateModel.setUsername(username);
        accountCreateModel.setPassword(hashingUtil.getHashPassword(password));
        accountCreateModel.setIvParameterSpec(encryptionComponent.getIvParameterSpec());
        accountCreateModel.setSecretKey(encryptionComponent.getSecretKey());

       return loginRepo.saveData();

    }

}
