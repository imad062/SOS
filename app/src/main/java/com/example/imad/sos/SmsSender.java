package com.example.imad.sos;

import android.telephony.SmsManager;

public class SmsSender {

    public SmsSender(String phoneNum, String message)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNum,null,message,null,null);
    }

    public SmsSender() {

    }

    public void sendSms(String phoneNum, String message)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNum,null,message,null,null);
    }
}
