package com.example.testeserial;

import android.os.Handler;
import android.renderscript.ScriptIntrinsicYuvToRGB;

import android_serialport_api.hyperlcd.BaseReader;
import android_serialport_api.hyperlcd.TransformUtils;

public abstract class Rs485Reader extends BaseReader {

    Runnable r = () -> this.IsSending = false;
    Handler h = new Handler();

    private int _baudrate = 0;

    public boolean IsSending = false;

    public Rs485Reader(int baudrate){
        _baudrate = baudrate;
    }

    public void BlockRead(){
        h.removeCallbacks(r);
        IsSending = true;
    }

    public void ReleaseRead(){
        int delay = calculateReadDeadZoneDelay(6, _baudrate);
        h.postDelayed(r, delay + 80);
    }

    private int calculateReadDeadZoneDelay(int bites, int baudrate){
        int offset = 80;

        int bits = 10;
        int delay = offset + 1000*bites*bits/baudrate;

        return delay;
    }
}
