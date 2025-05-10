package com.example.Account.Home;
import com.example.Account.Encryption.Encryption;
import com.example.Account.Encryption.KeyConverter;
import com.example.Account.JDBC.HomeRepo;
import com.example.Account.JDBC.LoginRepo;
import com.example.Account.Login.LoginModelClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class HomeService {
    @Autowired
    private HomeModel homeModel;
    @Autowired
    private HomeRepo homeRepo;
    @Autowired
    private LoginRepo loginRepo;
    @Autowired
    private LoginModelClass loginModelClass;
    public String homeServiceSaveData(String originalUsername, String name,
                                    String userName, String password, String favourite) throws
            InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        String returnValue;
        boolean doesSave;

        Encryption encryption = new Encryption();

        Boolean getValue = loginRepo.getData(originalUsername);

        if (getValue) {
            homeModel.setName(name);
            homeModel.setUsername(userName);
            homeModel.setPassword(encryption.encrypt(password,loginModelClass.getKey(),
                    loginModelClass.getIvParameterSpec()));

            doesSave = homeRepo.saveHomeData(originalUsername + "Home", favourite);
        }else {
            doesSave = false;
        }
       if (doesSave){
           returnValue = "data save successfully";
           System.out.println("data save successfully");
           return returnValue;
       }else {
           returnValue = "data does not save";
           System.out.println("data does not save");
           return returnValue;
       }
    }
}
