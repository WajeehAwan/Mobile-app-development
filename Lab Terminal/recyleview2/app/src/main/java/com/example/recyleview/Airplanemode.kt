package com.example.recyleview

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class Airplanemode : Service() {

    val TAG ="MyService"

    override fun onBind(intent: Intent): IBinder?=null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val datastring= intent?.getStringExtra("ExtraData")
        datastring?.let{
            Log.d(TAG,datastring)
        }
        Thread{
            while (true){}
        }.start()
        Log.d(TAG,"Service has been Running")
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"Service has been Killed")
    }
}