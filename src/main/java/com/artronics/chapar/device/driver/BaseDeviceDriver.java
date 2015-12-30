package com.artronics.chapar.device.driver;

import com.artronics.gsdwn.core.event.DevicePacketReceived;
import com.artronics.gsdwn.core.models.packet.DevicePacket;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDeviceDriver
{
    private final static Logger log = Logger.getLogger(BaseDeviceDriver.class);
    private static final int MAX_PACKET_LENGTH = 255;

    @Autowired
    private ApplicationEventPublisher publisher;

    protected InputStream input=null;
    protected OutputStream output=null;

    protected String driverName= this.toString();

    @Value("${com.artronics.chapar.device.id}")
    protected Long deviceId;

    @Value("${com.artronics.chapar.device.connection.connection_string}")
    protected String connectionString;

    @Value("${com.artronics.chapar.device.connection.timeout}")
    protected Integer timeout;

    protected void bufferReceived(){
        try {

            final byte[] buff = new byte[MAX_PACKET_LENGTH];
            final int length = input.read(buff, 0, MAX_PACKET_LENGTH);
            final ArrayList<Integer> intBuff = new ArrayList<>(length);
            for (int i = 0; i < length; i++) {
                //convert signed value to unsigned
                intBuff.add(buff[i] & 0xFF);
            }

            fireDevicePacketReceived(intBuff);

        }catch (IOException e) {
            e.printStackTrace();
            log.error("IO exp while reading buffer from driver:" + getDriverName());
        }
    }

    public void fireDevicePacketReceived(List<Integer> buffer){
        DevicePacket packet = new DevicePacket(buffer,deviceId);
        DevicePacketReceived event = new DevicePacketReceived(this, packet);
        publisher.publishEvent(event);
    }

    public String getDriverName()
    {
        return driverName;
    }
}
