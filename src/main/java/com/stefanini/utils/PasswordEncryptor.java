package com.stefanini.utils;

import java.util.Base64;

public abstract class PasswordEncryptor {
    public static String encrypt(String password) {
        return new String(Base64.getEncoder().encode(password.getBytes()));
    }

    public static String descrypt(String encryptPassword) {
        return new String(Base64.getDecoder().decode(encryptPassword.getBytes()));
    }
}
