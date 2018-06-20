package com.epam.movierating.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPasswordUtil {
    private HashPasswordUtil() {}

    /**
     * Hashing password used sha1 algorithm.
     * @param input - string for hashing
     * @return hashing password
     */
    public static String sha1(String input){
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            byte[] result = messageDigest.digest(input.getBytes());
            for (int i =0;i< result.length;i++)
            {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  sb.toString();
    }
}
