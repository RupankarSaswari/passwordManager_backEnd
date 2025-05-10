package com.example.Account.Encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptionComponent {
    private SecretKey secretKey;
    private IvParameterSpec ivParameterSpec;

    public IvParameterSpec getIvParameterSpec() {

        IvGenerator();

        return ivParameterSpec;
    }

    public SecretKey getSecretKey() throws NoSuchAlgorithmException {

        secretKeyGenerator();

        return secretKey;
    }

    private void secretKeyGenerator() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        secretKey = keyGenerator.generateKey();
    }

    private void IvGenerator() {

        byte [] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        ivParameterSpec = new IvParameterSpec(iv);
    }
}
