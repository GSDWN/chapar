package com.artronics.chapar.device;

import com.artronics.chapar.device.driver.DeviceDriver;
import com.artronics.chapar.exception.DeviceConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DeviceDriverServiceImpl implements DeviceDriverService
{
    @Autowired
    DeviceDriver deviceDriver;

    @PostConstruct
    public void initBean(){
        deviceDriver.init();
    }

    @Override
    public void open() throws DeviceConnectionException
    {
        deviceDriver.open();
    }

    @Override
    public void close() throws DeviceConnectionException
    {
        deviceDriver.close();
    }
}
