package ru.test.smsmessenger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast


class SmsMonitor : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent?.action != null && ACTION.compareTo(intent?.action) == 0) {
            val pduArray = intent?.extras.get("pdus") as Array<Any>
            val messages = arrayOfNulls<SmsMessage>(pduArray.size)
            for (i in pduArray.indices) {
                Toast.makeText(context.applicationContext,(SmsMessage.createFromPdu(pduArray[i] as ByteArray)).messageBody, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        private val ACTION = "android.provider.Telephony.SMS_RECEIVED"
    }
}