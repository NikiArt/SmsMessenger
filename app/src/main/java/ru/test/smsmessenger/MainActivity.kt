package ru.test.smsmessenger

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var sendButton: Button
    lateinit var cancelButton: Button
    lateinit var telNumber: EditText
    lateinit var textSms: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendButton = findViewById(R.id.activity_main_button_send)
        cancelButton = findViewById(R.id.activity_main_button_cancel)
        telNumber = findViewById(R.id.activity_main_tel_number)
        textSms = findViewById(R.id.activity_main_sms_text)

        sendButton.setOnClickListener { sendSms() }
        cancelButton.setOnClickListener { cancelSms() }
    }

    private fun cancelSms() {
        if (!textSms.text.isEmpty()) textSms.text.clear()
    }

    private fun sendSms() {
        if (!textSms.text.isEmpty()) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                    SmsManager.getDefault()
                        .sendTextMessage(telNumber.text.toString(), null, textSms.text.toString(), null, null);
                } else {
                    ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.SEND_SMS), 0)
                }
            }
        } else {
            Toast.makeText(applicationContext, "Введите текст сообщения!", Toast.LENGTH_LONG).show()
        }
    }
}
