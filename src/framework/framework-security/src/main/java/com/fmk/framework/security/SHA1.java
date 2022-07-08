package com.fmk.framework.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.Charsets;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1 {
    private static final String ALGORITHM_SHA1 = "SHA1";
    private static final String ALGORITHM_SHA256 = "SHA-256";
//    public static String shaPassword(String pwd, String salt){
//        byte[] bytes = sha1(pwd.getBytes(), salt.getBytes());
//        return Hex.encodeHexString(bytes);
//    }
//    public static String sha1(String pwd){
//        byte[] bytes = Digests.sha1(pwd.getBytes());
//        return Hex.encodeHexString(bytes);
//    }
    public static String shaPassword(String pwd, String salt){
        byte[] bytes = sha1(pwd.getBytes(), salt.getBytes());
        return Hex.encodeHexString(bytes);
    }

    public static String sha256(byte[] data) {
        return sha(data, ALGORITHM_SHA256);
    }

    public static String sha256(String data) {
        return sha(data, ALGORITHM_SHA256);
    }

    public static String sha1(byte[] data) {
        return sha(data, ALGORITHM_SHA1);
    }

    public static String sha1(String data) {
        return sha(data, ALGORITHM_SHA1);
    }
    private static String sha(byte[] data, String algorithm) {
        try {
            byte[] digestData = MessageDigest.getInstance(algorithm).digest(data);
            return Hex.encodeHexString(digestData);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String sha(String data, String algorithm) {
        try {
            return sha(data.getBytes(Charsets.UTF_8.name()), algorithm);
        }catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
    private static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, ALGORITHM_SHA1, salt, 1);
    }
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest e = MessageDigest.getInstance(algorithm);
            if (salt != null) {
                e.update(salt);
            }

            byte[] result = e.digest(input);

            for (int i = 1; i < iterations; ++i) {
                e.reset();
                result = e.digest(result);
            }

            return result;
        } catch (GeneralSecurityException arg6) {
            throw new RuntimeException(arg6);
        }
    }
}
