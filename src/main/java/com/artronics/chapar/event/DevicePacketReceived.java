package com.artronics.chapar.event;

import com.artronics.gsdwn.core.models.packet.DevicePacket;

public class DevicePacketReceived extends BaseEvent<DevicePacket>
{
    public DevicePacketReceived(Object source, DevicePacket message)
    {
        super(source, message);
    }
}
