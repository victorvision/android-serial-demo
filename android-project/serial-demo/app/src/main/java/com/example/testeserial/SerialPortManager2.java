package com.example.testeserial;

import java.io.File;
import java.io.IOException;

import android_serialport_api.SerialPort;

public class SerialPortManager2 extends SerialPort {

    public SerialPortManager2(File device, int baudrate, int flags) throws SecurityException, IOException {
        super(device, baudrate, flags);
    }
}
