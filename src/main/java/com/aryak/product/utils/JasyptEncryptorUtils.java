package com.aryak.product.utils;


import org.jasypt.util.text.BasicTextEncryptor;

public class JasyptEncryptorUtils {

    public static void main(String[] args) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("pass");
        String encryptedValue = textEncryptor.encrypt("admin");
        System.out.println("Encrypted Value: " + encryptedValue);
    }
}
