package com.example.emitter.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.emitter.R

class SendBroadcastAct : AppCompatActivity() {

    lateinit var name:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_broadcast)
         name = intent.getStringExtra("Name").toString()
     broadcastIntent()
    }

    fun broadcastIntent()
    {
        val intent = Intent()
        intent.action = "com.example.emitter.broadcast"
        intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        intent.putExtra("Name", name)
        sendBroadcast(intent)
       //   Toast.makeText(this@SendBroadcastAct,name+" recived and sent to MiddleMan", Toast.LENGTH_SHORT).show()
    }
}