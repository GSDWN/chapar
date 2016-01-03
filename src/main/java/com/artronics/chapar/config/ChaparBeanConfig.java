package com.artronics.chapar.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = {
        "com.artronics.chapar.device"
})
@PropertySource("classpath:application.properties")
public class ChaparBeanConfig
{
    private final static Logger log = Logger.getLogger(ChaparBeanConfig.class);

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
