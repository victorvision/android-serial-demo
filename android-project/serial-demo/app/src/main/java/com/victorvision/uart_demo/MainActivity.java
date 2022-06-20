package com.victorvision.uart_demo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android_serialport_api.hyperlcd.BaseReader;
import android_serialport_api.hyperlcd.SerialEnums;
import android_serialport_api.hyperlcd.SerialPortManager;

public class MainActivity extends AppCompatActivity implements Runnable {
    SerialPortManager spManager = SerialPortManager.getInstances();
    //    Handler handler;
    boolean isAscii = false;
    TextView textViewReceived;

    int baudrate0 = 115200;
    int baudrate1 = 115200;
    int baudrate3 = 115200;
    int baudrate4 = 115200;

    BaseReader baseReader0;
    BaseReader baseReader1;
    BaseReader baseReader3;
    BaseReader baseReader4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewReceived = (TextView) findViewById(R.id.textViewReceived);
//        handler = new Handler();
//        handler.postDelayed((Runnable) this, 1);

        baseReader0 = new BaseReader() {
            @Override
            protected void onParse(final String port, final boolean isAscii, final String read) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("SERIAL_RECEIVED_0", read);
                        textViewReceived.setText("Porta 0: "+read);
                    }
                });
            }
        };

        baseReader1 = new BaseReader() {
            @Override
            protected void onParse(final String port, final boolean isAscii, final String read) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("SERIAL_RECEIVED_1", read);
                        textViewReceived.setText("Porta 1: "+read);
                    }
                });
            }
        };

        baseReader3 = new BaseReader() {
            @Override
            protected void onParse(final String port, final boolean isAscii, final String read) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("SERIAL_RECEIVED_3", read);
                        textViewReceived.setText("Porta 3: "+read);

                    }
                });
            }
        };

        baseReader4 = new BaseReader() {
            @Override
            protected void onParse(final String port, final boolean isAscii, final String read) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("SERIAL_RECEIVED_4", read);
                        textViewReceived.setText("Porta 4: "+read);

                    }
                });
            }
        };

        spManager.startSerialPort(SerialEnums.Ports.ttyS0, isAscii, baudrate0, 0, baseReader0);
        spManager.startSerialPort(SerialEnums.Ports.ttyS1, isAscii, baudrate1, 0, baseReader1);
        //spManager.startSerialPort(SerialEnums.Ports.ttyS3, isAscii, baudrate3, 0, baseReader3); // PORTA SERIAL 3 NÃO DISPONÍVEL NO MODELO MSC
        spManager.startSerialPort(SerialEnums.Ports.ttyCOM0, isAscii, baudrate4, 0, baseReader4);
    }

    @Override
    public void run() {
//        int result = spManager.send(SerialEnums.Ports.ttyS1, "Mensagem de Teste Porta 1");
//        handler.postDelayed((Runnable) this, 500);
    }

    public void buttonSerial0(View view) {
        if (isAscii) {
            spManager.send(SerialEnums.Ports.ttyS0, isAscii, "Mensagem de Teste Porta 0");
        } else {
            spManager.send(SerialEnums.Ports.ttyS0, isAscii, "00112233aaff");
        }
    }

    public void buttonSerial1(View view) {
        if (isAscii) {
            spManager.send(SerialEnums.Ports.ttyS1, isAscii, "Mensagem de Teste Porta 1");
        } else {
            spManager.send(SerialEnums.Ports.ttyS1, isAscii, "00112233aaff");
        }
    }

    public void buttonSerial3(View view) {
        if (isAscii) {
            spManager.send(SerialEnums.Ports.ttyS3, isAscii, "Mensagem de Teste Porta 3");
        } else {
            spManager.send(SerialEnums.Ports.ttyS3, isAscii, "00112233aaff");
        }
    }

    public void buttonSerial4(View view) {
        if (isAscii) {
            spManager.send(SerialEnums.Ports.ttyCOM0, isAscii, "Mensagem de Teste Porta 4");
        } else {
            spManager.send(SerialEnums.Ports.ttyCOM0, isAscii, "00112233aaff");
        }
    }

    public void clearTextView(View view) {
        textViewReceived.setText("Nada Recebido");
    }
}