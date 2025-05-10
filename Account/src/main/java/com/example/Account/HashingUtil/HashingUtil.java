package com.example.Account.HashingUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashingUtil {

    private String hashPassword;
    private String masterPassword;

    public String getHashPassword(String masterPassword) throws UnsupportedEncodingException {
        this.masterPassword = masterPassword;
        sha256();
        return hashPassword;
    }

    private void sha256() throws UnsupportedEncodingException {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(masterPassword.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            hashPassword = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
