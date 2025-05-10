package com.example.Account.Encryption;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encryption {

    public String encrypt(String password, String secretKey, String ivParameterSpec) throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        KeyConverter keyConverter = new KeyConverter();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE,
                keyConverter.StringToSecretKey(secretKey),
                keyConverter.StringToIvParameterSpec(ivParameterSpec));

        byte [] cipherText = cipher.doFinal(password.getBytes());

        return Base64.getEncoder().encodeToString(cipherText);
    }
}
