package com.artronics.chapar.device.driver;

import com.artronics.chapar.exception.DeviceConnectionException;

public interface DeviceDriver
{
    void open() throws DeviceConnectionException;

    void close() throws DeviceConnectionException;
}
