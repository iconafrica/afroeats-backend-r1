package com.iconsoft.afroeats.Configuration.Security.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class AppProperties {
    @Autowired
    private Environment environment;

    public String getTokenSecret() {
        return environment.getProperty("token.secret");
    }

}
