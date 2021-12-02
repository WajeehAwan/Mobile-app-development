package com.example.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val channe = "chanelId"
    private val not_id =101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotic()
        btn.setOnClickListener(){
            notifsend()
        }



    }

    private fun createNotic(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name= "Notification titile"
            val discri = "notifcation is no more"
            val impo :Int = NotificationManager.IMPORTANCE_DEFAULT

            val channel:NotificationChannel= NotificationChannel(channe,name,impo).apply {
                description =discri
            }
            val notif : NotificationManager= getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
            notif.createNotificationChannel(channel)


        }


     }
    private fun notifsend(){
        val intent = Intent(this,MainActivity::class.java).apply {
            flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent:PendingIntent = PendingIntent.getActivity(this,0,intent,0)
        val bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.face)
        val KEY_TEXT_REPLY = "key_text_reply"
        var replyLabel: String = resources.getString(R.string.reply_label)
        var remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
            setLabel(replyLabel)
            build()
        }
        var action: NotificationCompat.Action =
            NotificationCompat.Action.Builder(R.drawable.anal,
                getString(R.string.reply_label), pendingIntent)
                
                .build()
        val builder:NotificationCompat.Builder = NotificationCompat.Builder(this,channe)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setLargeIcon(bitmap)
            .setContentTitle("Notification")
            .setContentText("hi i am behram")
            .setStyle(NotificationCompat.BigTextStyle().bigText("hi big text"))
            .setContentIntent(pendingIntent)
            .addAction(action)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            with(NotificationManagerCompat.from(this)){
            notify(not_id,builder.build())
        }
    }
}