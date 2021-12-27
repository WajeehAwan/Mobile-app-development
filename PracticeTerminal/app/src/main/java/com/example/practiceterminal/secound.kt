package com.example.practiceterminal

import android.app.Notification.EXTRA_TEXT
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class secound : AppCompatActivity() {
    val CHANNEL_ID = "chanel-id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secound)
        val message = intent.getStringExtra(EXTRA_TEXT)
        val btnnotify:Button = findViewById(R.id.btnnotify)
        // Capture the layout's TextView and set the string as its text
        val textView = findViewById<TextView>(R.id.textView).apply {
            text = message
        }
        textView.setText(message)
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        createNotificationChannel()
        btnnotify.setOnClickListener {
            notifsend()
        }

        val btnEmail:Button=findViewById(R.id.btnEmail2)
        val Email:EditText=findViewById(R.id.emailto)
        val Subject:EditText=findViewById(R.id.Subject)
        val Body:EditText=findViewById(R.id.Body)

        btnEmail.setOnClickListener {
            val recipient = Email.text.toString().trim()
            val subject = Subject.text.toString().trim()
            val message = Body.text.toString().trim()

            //method call for email intent with these inputs as parameters
            sendEmail(recipient, subject, message)
        }

        }

    private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)
        try {
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }
    private fun createNotificationChannel() {

        val channel_name = "This a notification"
        val destxt = "I am a newly made notificaton"
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = getString(R.string.channel_name)
//            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, channel_name, importance).apply {
                description = destxt
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

private fun notifsend(){
    val intent = Intent(this,MainActivity::class.java).apply {
        flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(this,0,intent,0)
    val bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.ic_baseline_notifications_24)
    val KEY_TEXT_REPLY = "key_text_reply"
    var replyLabel: String = resources.getString(R.string.reply_label)
    var remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY).run {
        setLabel(replyLabel)
        build()
    }
    var action: NotificationCompat.Action =
        NotificationCompat.Action.Builder(R.drawable.ic_baseline_notifications_24,
            getString(R.string.reply_label), pendingIntent)

            .build()
    val builder:NotificationCompat.Builder = NotificationCompat.Builder(this,CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setLargeIcon(bitmap)
        .setContentTitle("Notification")
        .setContentText("hi i am behram")
        .setStyle(NotificationCompat.BigTextStyle().bigText("hi big text"))
        .setContentIntent(pendingIntent)
        .addAction(action)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    with(NotificationManagerCompat.from(this)){
        notify(101,builder.build())
    }
}
}