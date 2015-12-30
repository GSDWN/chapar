package com.artronics.chapar.device.driver.serialPort;

import com.artronics.chapar.device.driver.BaseDeviceDriver;
import com.artronics.gsdwn.core.device.driver.DeviceDriver;
import com.artronics.gsdwn.core.exception.DeviceConnectionException;
import gnu.io.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;
import java.util.TooManyListenersException;

@Component("com.artronics.chapar.device.driver.serialPort.SerialPortDriverImpl")
public class SerialPortDriverImpl extends BaseDeviceDriver implements SerialPortDriver, DeviceDriver, SerialPortEventListener
{
    private final static Logger log = Logger.getLogger(SerialPortDriverImpl.class);
    private CommPortIdentifier identifier;
    private SerialPort serialPort;

    @Override
    public void init()
    {
        setConnection();
    }

    private void setConnection()
    {
        Enumeration portsEnum = CommPortIdentifier.getPortIdentifiers();

        log.debug("Searching for available serial ports:");
        int count=0;
        while (portsEnum.hasMoreElements()) {
            count++;
            CommPortIdentifier port = (CommPortIdentifier) portsEnum.nextElement();

            log.debug(count + " : \""+port.getName()+"\"");
            if (port.getName().equals(connectionString)) {
                identifier = port;


                log.debug("Match found.");
                return;
            }
        }
        log.debug("No match found. Remember Connection String must be equal to com port's name");
    }

    @Override
    public void open() throws DeviceConnectionException
    {
        log.debug("Opening Serial port...");

        if (identifier == null) {
            throw new DeviceConnectionException("Attempt to open a null connection.");
        }

        final CommPort commPort;
        SerialPort serialPort = null;

        try {
            commPort = identifier.open("SinkPort", timeout);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort) commPort;

            serialPort.setSerialPortParams(115200,
                                           SerialPort.DATABITS_8,
                                           SerialPort.STOPBITS_1,
                                           SerialPort.PARITY_NONE);


        }catch (Exception e) {
            e.printStackTrace();
            throw new DeviceConnectionException("Can not open the connection");
        }

        if (serialPort != null) {
            this.serialPort = serialPort;
            initEventListenersAndIO();

        }else
            throw new DeviceConnectionException("No serial port has found. serialPort is null");
    }

    @Override
    public void close() throws DeviceConnectionException
    {
        try {
            serialPort.close();
            input.close();
            output.close();

        }catch (IOException e) {
            e.printStackTrace();
            throw new DeviceConnectionException("IO exp while closing connection");
        }
    }
    private void initEventListenersAndIO() throws DeviceConnectionException
    {
        try {
            log.debug("Initializing Event Listener, Input and Output Streams.");
            input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);

        }catch (TooManyListenersException e) {
            e.printStackTrace();
            log.error("Too many listener");
            throw new DeviceConnectionException("Too many listener");
        }catch (IOException e) {
            e.printStackTrace();
            throw new DeviceConnectionException("IO exception while opening streams.");
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent)
    {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            bufferReceived();
        }
    }
}
