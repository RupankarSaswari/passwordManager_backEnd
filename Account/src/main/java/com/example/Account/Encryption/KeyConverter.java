package com.example.Account.Encryption;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class KeyConverter {
    protected SecretKey StringToSecretKey(String stringKey){

        byte[] decodeKey = Base64.getDecoder().decode(stringKey);

        return new SecretKeySpec(decodeKey, 0, decodeKey.length,"AES");
    }

    public String secretKeyToString(SecretKey secretKey) {
        byte[] keyBytes = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    protected IvParameterSpec StringToIvParameterSpec(String encodedIv) {
        byte[] ivBytes = Base64.getDecoder().decode(encodedIv);
        return new IvParameterSpec(ivBytes);
    }

    public String ivParameterSpecToString(IvParameterSpec ivParameterSpec) {
        byte[] ivBytes = ivParameterSpec.getIV();
        return Base64.getEncoder().encodeToString(ivBytes);
    }
}
