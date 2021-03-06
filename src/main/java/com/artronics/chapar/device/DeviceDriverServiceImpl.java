package com.artronics.chapar.device;

import com.artronics.gsdwn.core.device.DeviceDriverService;
import com.artronics.gsdwn.core.device.driver.DeviceDriver;
import com.artronics.gsdwn.core.exception.DeviceConnectionException;
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
