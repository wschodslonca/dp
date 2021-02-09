package com.project.dp;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;


public class PasswordCrypter {
    public static String md5(String s) {
        try {
            byte[] bytesOfMessage = s.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            byte[] encoded = Base64.getEncoder().encode(thedigest);
            return new String(encoded);
        }
        catch (Exception e) {
            return null;
        }
    }
}
