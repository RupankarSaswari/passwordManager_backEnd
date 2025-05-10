package com.example.Account.Encryption;

import com.example.Account.Login.LoginModelClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
@Component
public class Decoder {
    @Autowired
    private LoginModelClass loginModelClass;

    public String decrypt(String cipherText) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        KeyConverter keyConverter = new KeyConverter();

        SecretKey secretKey = keyConverter.StringToSecretKey(loginModelClass.getKey()); ;
        IvParameterSpec ivParameterSpec =
                keyConverter.StringToIvParameterSpec(loginModelClass.getIvParameterSpec());

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,secretKey, ivParameterSpec );

        byte[] originalText = cipher.doFinal(Base64.getDecoder().decode(cipherText));

        return new String(originalText);
    }
}
