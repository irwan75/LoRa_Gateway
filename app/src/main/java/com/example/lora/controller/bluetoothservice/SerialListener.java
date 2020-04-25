package com.example.lora.controller.bluetoothservice;

public interface SerialListener {

    void onSerialConnect      ();
    void onSerialConnectError (Exception e);
    void onSerialRead         (byte[] data);
    void onSerialIoError      (Exception e);

}
