package com.artronics.chapar;

import com.artronics.chapar.device.DeviceDriverService;
import com.artronics.gsdwn.core.event.DevicePacketReceived;
import com.artronics.gsdwn.core.exception.DeviceConnectionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class ChaparApplication implements ApplicationListener<ContextRefreshedEvent>
{

    private final static Logger log = Logger.getLogger(ChaparApplication.class);

    @Autowired
    DeviceDriverService service;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        try {
            service.open();
        }catch (DeviceConnectionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
		SpringApplication.run(ChaparApplication.class, args);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

    @EventListener
    public void devicePacketReceivedHandler(DevicePacketReceived event){
        log.debug("New buffer received");
    }
}
