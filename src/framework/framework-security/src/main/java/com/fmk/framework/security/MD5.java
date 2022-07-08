package com.fmk.framework.security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.Charsets;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jerry on 16-6-1.
 */
public class MD5 {
    public static String digest(byte[] data) {
        try {
            byte[] digestData = MessageDigest.getInstance("MD5").digest(data);
            return new String(Hex.encodeHex(digestData));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String digest(String data) {
        try {
            return digest(data.getBytes(Charsets.UTF_8.name()));
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
