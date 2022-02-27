package com.iconsoft.afroeats.Configuration;

import java.security.SecureRandom;

public class GenerateRandom {
    static SecureRandom rnd = new SecureRandom();

    public static String randomString(int len){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        System.out.println("*************************"+sb.toString());
        return sb.toString();
    }
}
