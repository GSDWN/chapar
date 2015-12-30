package com.artronics.chapar.device;

import com.artronics.chapar.exception.DeviceConnectionException;

public interface DeviceDriverService
{
    void open() throws DeviceConnectionException;

    void close() throws DeviceConnectionException;
}
