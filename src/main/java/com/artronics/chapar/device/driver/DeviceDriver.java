package com.artronics.chapar.device.driver;

import com.artronics.gsdwn.core.exception.DeviceConnectionException;

public interface DeviceDriver
{
    void init();

    void open() throws DeviceConnectionException;

    void close() throws DeviceConnectionException;
}
