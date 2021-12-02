package com.example.receiver

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var receiver:MyReceiver
    lateinit var player: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var start_btn=findViewById<Button>(R.id.button2)
        var stop_btn=findViewById<Button>(R.id.button3)
        var reg_recv=findViewById<Button>(R.id.button4)
        receiver= MyReceiver()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {

            registerReceiver(receiver, it)
        }
        start_btn.setOnClickListener({
            //startService(Intent(this,mediaplayerservice::class.java))
            var uri= Uri.parse("file:///android_res/raw/file.mp3");
            player= MediaPlayer.create(this, R.raw.file)
            player.isLooping=true
            player.start()
        })
        stop_btn.setOnClickListener({
            //stopService(Intent(this,mediaplayerservice::class.java))
            super.onDestroy()
            player.stop()
        })
        reg_recv.setOnClickListener({
            IntentFilter("custom_intent").also {
                registerReceiver(mMessageReceiver,it)
            }
        })
    }

    fun broadcast(view: View) {
        var intent= Intent()
        intent.setAction("custom_intent")
        sendBroadcast(intent)
    }
    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Extract data included in the Intent
            val message = intent.action
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }
}