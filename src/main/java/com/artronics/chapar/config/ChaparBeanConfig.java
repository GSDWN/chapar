package com.artronics.chapar.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {
        "com.artronics.chapar.device"
})
@PropertySource("classpath:application.properties")
public class ChaparBeanConfig
{
    private final static Logger log = Logger.getLogger(ChaparBeanConfig.class);
}
