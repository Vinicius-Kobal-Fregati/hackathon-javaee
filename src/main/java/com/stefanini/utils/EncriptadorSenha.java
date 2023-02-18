package com.stefanini.utils;

import java.util.Base64;

public abstract class EncriptadorSenha {
    public static String encripta(String password) {
        return new String(Base64.getEncoder().encode(password.getBytes()));
    }

    public static String descripta(String encryptPassword) {
        return new String(Base64.getDecoder().decode(encryptPassword.getBytes()));
    }
}
