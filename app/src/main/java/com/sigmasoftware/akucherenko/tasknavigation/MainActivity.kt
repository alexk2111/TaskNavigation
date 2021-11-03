package com.sigmasoftware.akucherenko.tasknavigation

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button

class MainActivity : AppCompatActivity() {

    val messageReceiver: MessageReceiver = MessageReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val crashButton = Button(this)
//        crashButton.text = "Test Crash"
//        crashButton.setOnClickListener {
//            throw RuntimeException("Test Crash") // Force a crash
//        }
//
//        addContentView(crashButton, ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter(MessageReceiver.MESSAGE_RECEIVER_ACTION)
        registerReceiver(messageReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(messageReceiver)
    }
}