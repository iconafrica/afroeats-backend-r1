package com.iconsoft.afroeats.Configuration.Security.Configuration;

import com.iconsoft.afroeats.Configuration.Security.Properties.AppProperties;
import com.iconsoft.afroeats.SpringApplicationContext;

public class SecurityConstant {

    public static final String SECRET="donald.sop@digiplusconsulting.com";
    public static final long EXPIRATION_TIME=864_000_000;//10days
    public static final String TOKEN_PREFIX="Bearer ";
    public static final String HEADER_STRING="Authorization";

    public static final long RESET_PASSWORD_EXPIRATION_TIME = 3600000; //1 Hours

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        System.out.println("------------------------Key : "+appProperties.getTokenSecret());
        return appProperties.getTokenSecret();
    }

}
