package com.example.practiceterminal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneMode :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val isairplanemode = intent?.getBooleanExtra("state",false)?:return
        if(isairplanemode){
            Toast.makeText(context, "Airplane mode is Enabled",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,"Airplanr Mode is Diabled", Toast.LENGTH_LONG).show()
        }
    }
}