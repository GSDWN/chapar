package com.artronics.chapar.packet;

import org.apache.log4j.Logger;

import java.util.List;

public class DevicePacket
{
    private final static Logger log = Logger.getLogger(DevicePacket.class);

    private final List<Integer> buffer;
    private final Long deviceId;

    public DevicePacket(List<Integer> buffer, Long deviceId)
    {
        this.buffer = buffer;
        this.deviceId = deviceId;
    }

    public List<Integer> getBuffer()
    {
        return buffer;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }
}
