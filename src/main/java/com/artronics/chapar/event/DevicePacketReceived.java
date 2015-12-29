package com.artronics.chapar.event;

import com.artronics.chapar.packet.DevicePacket;

public class DevicePacketReceived extends BaseEvent<DevicePacket>
{
    public DevicePacketReceived(Object source, DevicePacket message)
    {
        super(source, message);
    }
}
